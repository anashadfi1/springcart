import { Component, Input, Output, EventEmitter, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Product, MOCK_PRODUCTS } from '../../models/Product.model';
import { ProductCardComponent } from '../product-card/product-card';
import { CartItem } from '../product-details/product-details';

interface Category { key: string; label: string; }
interface PriceRange { key: string; label: string; min: number; max: number; }

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ProductCardComponent],
  templateUrl: './product-list.html',
})
export class ProductListComponent implements OnInit, OnChanges {
  /** Allow parent to inject products (falls back to mock data) */
  @Input() allProducts: Product[] = MOCK_PRODUCTS;
  /** External search query driven by the Navbar */
  @Input() externalSearch = '';

  @Output() addedToCart     = new EventEmitter<CartItem>();
  @Output() wishlistToggled = new EventEmitter<Product>();
  @Output() cardClicked     = new EventEmitter<Product>();

  // ─── Filter State ─────────────────────────────────────────────────────────
  searchQuery      = '';
  selectedCategory = 'all';
  sortBy           = 'featured';
  priceRange       = 'all';
  inStockOnly      = false;
  gridCols         = 3;

  filteredProducts: Product[] = [];
  hasMore = false;

  private readonly PAGE_SIZE = 9;
  private currentPage = 1;

  // ─── Static Options ───────────────────────────────────────────────────────
  categories: Category[] = [
    { key: 'all',        label: 'All'         },
    { key: 'laptop',     label: 'Laptops'     },
    { key: 'smartphone', label: 'Phones'      },
    { key: 'audio',      label: 'Audio'       },
    { key: 'tablet',     label: 'Tablets'     },
    { key: 'accessory',  label: 'Accessories' },
  ];

  private priceRanges: PriceRange[] = [
    { key: 'all',       label: 'All Prices',       min: 0,    max: Infinity },
    { key: '0-500',     label: 'Under $500',       min: 0,    max: 500      },
    { key: '500-1000',  label: '$500 – $1,000',    min: 500,  max: 1000     },
    { key: '1000-2000', label: '$1,000 – $2,000',  min: 1000, max: 2000     },
    { key: '2000+',     label: '$2,000+',           min: 2000, max: Infinity },
  ];

  // ─── Lifecycle ────────────────────────────────────────────────────────────
  ngOnInit(): void {
    this.applyFilters();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['externalSearch']) {
      this.searchQuery = this.externalSearch;
      this.applyFilters();
    }
    if (changes['allProducts']) {
      this.applyFilters();
    }
  }

  // ─── Filter Logic ─────────────────────────────────────────────────────────
  selectCategory(key: string): void {
    this.selectedCategory = key;
    this.currentPage = 1;
    this.applyFilters();
  }

  applyFilters(): void {
    let result = [...this.allProducts];

    // Category
    if (this.selectedCategory !== 'all') {
      result = result.filter(p => p.category === this.selectedCategory);
    }

    // Search
    const q = this.searchQuery.trim().toLowerCase();
    if (q) {
      result = result.filter(p =>
        p.name.toLowerCase().includes(q)  ||
        p.brand.toLowerCase().includes(q) ||
        p.specs.some(s => s.toLowerCase().includes(q))
      );
    }

    // Price range
    const range = this.priceRanges.find(r => r.key === this.priceRange);
    if (range && range.key !== 'all') {
      result = result.filter(p => p.price >= range.min && p.price <= range.max);
    }

    // In stock
    if (this.inStockOnly) {
      result = result.filter(p => p.inStock);
    }

    // Sort
    switch (this.sortBy) {
      case 'price-asc':  result.sort((a, b) => a.price - b.price); break;
      case 'price-desc': result.sort((a, b) => b.price - a.price); break;
      case 'rating':     result.sort((a, b) => b.rating - a.rating || b.reviews - a.reviews); break;
      case 'newest':     result.sort((a, b) => (b.badge === 'New' ? 1 : 0) - (a.badge === 'New' ? 1 : 0)); break;
    }

    const paginated = result.slice(0, this.currentPage * this.PAGE_SIZE);
    this.hasMore = paginated.length < result.length;
    this.filteredProducts = paginated;
  }

  loadMore(): void {
    this.currentPage++;
    this.applyFilters();
  }

  clearAllFilters(): void {
    this.searchQuery      = '';
    this.selectedCategory = 'all';
    this.priceRange       = 'all';
    this.inStockOnly      = false;
    this.sortBy           = 'featured';
    this.currentPage      = 1;
    this.applyFilters();
  }

  // ─── Helpers ──────────────────────────────────────────────────────────────
  hasActiveFilters(): boolean {
    return this.selectedCategory !== 'all' ||
           this.priceRange !== 'all'       ||
           this.inStockOnly                ||
           this.searchQuery.trim().length > 0;
  }

  getCategoryCount(key: string): number {
    if (key === 'all') return this.allProducts.length;
    return this.allProducts.filter(p => p.category === key).length;
  }

  getCategoryLabel(key: string): string {
    return this.categories.find(c => c.key === key)?.label ?? key;
  }

  getPriceLabel(): string {
    return this.priceRanges.find(r => r.key === this.priceRange)?.label ?? '';
  }

  // ─── Event Relays ─────────────────────────────────────────────────────────
  onAddToCart(product: Product): void {
    this.addedToCart.emit({ product, quantity: 1 });
  }

  onWishlistToggle(product: Product): void {
    product.wishlisted = !product.wishlisted;
    this.wishlistToggled.emit(product);
  }

  onCardClick(product: Product): void {
    this.cardClicked.emit(product);
  }
}