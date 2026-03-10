import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

export interface Product {
  id: number;
  name: string;
  brand: string;
  category: string;
  price: number;
  originalPrice?: number;
  rating: number;
  reviews: number;
  badge?: 'New' | 'Sale' | 'Hot';
  specs: string[];
  icon: string;         // SVG path 'd' attribute
  wishlisted: boolean;
}

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './products.html',
})
export class ProductsComponent implements OnInit {

  // ─── UI State ─────────────────────────────────────────────────────────────
  searchQuery      = '';
  selectedCategory = 'all';
  sortBy           = 'featured';
  gridCols         = 3;
  cartCount        = 0;

  // ─── Static Data ──────────────────────────────────────────────────────────
  stats = [
    { value: '2K+',  label: 'Products'  },
    { value: '50+',  label: 'Brands'    },
    { value: '4.9★', label: 'Avg Rating'},
  ];

  categories = [
    { key: 'all',        label: 'All'        },
    { key: 'laptop',     label: 'Laptops'    },
    { key: 'smartphone', label: 'Phones'     },
    { key: 'audio',      label: 'Audio'      },
    { key: 'tablet',     label: 'Tablets'    },
    { key: 'accessory',  label: 'Accessories'},
  ];

  // SVG path shortcuts
  private readonly ICONS = {
    laptop:     'M9 17.25v1.007a3 3 0 01-.879 2.122L7.5 21h9l-.621-.621A3 3 0 0115 18.257V17.25m6-12V15a2.25 2.25 0 01-2.25 2.25H5.25A2.25 2.25 0 013 15V5.25A2.25 2.25 0 015.25 3h13.5A2.25 2.25 0 0121 5.25z',
    phone:      'M10.5 1.5H8.25A2.25 2.25 0 006 3.75v16.5a2.25 2.25 0 002.25 2.25h7.5A2.25 2.25 0 0018 20.25V3.75a2.25 2.25 0 00-2.25-2.25H13.5m-3 0V3h3V1.5m-3 0h3m-3 15h3',
    headphones: 'M9 9a3 3 0 114.5 2.598M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25z',
    tablet:     'M10.5 19.5h3m-6.75 2.25h10.5a2.25 2.25 0 002.25-2.25v-15a2.25 2.25 0 00-2.25-2.25H6.75A2.25 2.25 0 004.5 4.5v15a2.25 2.25 0 002.25 2.25z',
    watch:      'M12 6v6h4.5m4.5 0a9 9 0 11-18 0 9 9 0 0118 0z',
    camera:     'M6.827 6.175A2.31 2.31 0 015.186 7.23c-.38.054-.757.112-1.134.175C2.999 7.58 2.25 8.507 2.25 9.574V18a2.25 2.25 0 002.25 2.25h15A2.25 2.25 0 0021.75 18V9.574c0-1.067-.75-1.994-1.802-2.169a47.865 47.865 0 00-1.134-.175 2.31 2.31 0 01-1.64-1.055l-.822-1.316a2.192 2.192 0 00-1.736-1.039 48.774 48.774 0 00-5.232 0 2.192 2.192 0 00-1.736 1.039l-.821 1.316z M16.5 12.75a4.5 4.5 0 11-9 0 4.5 4.5 0 019 0zM18.75 10.5h.008v.008h-.008V10.5z',
  };

  products: Product[] = [
    { id:1,  name:'ProBook X1 Ultra',      brand:'ASUS',     category:'laptop',     price:1299.99, originalPrice:1599.99, rating:5, reviews:248, badge:'Sale',  specs:['Intel i9','32GB RAM','1TB SSD'],        icon: this.ICONS.laptop,     wishlisted:false },
    { id:2,  name:'Galaxy S25 Edge',        brand:'Samsung',  category:'smartphone', price:999.00,                         rating:5, reviews:512, badge:'New',   specs:['6.7" AMOLED','200MP','5000mAh'],        icon: this.ICONS.phone,      wishlisted:false },
    { id:3,  name:'SoundPeak Pro ANC',      brand:'Sony',     category:'audio',      price:349.00,  originalPrice:429.00, rating:4, reviews:891, badge:'Hot',   specs:['40hr battery','ANC','Hi-Res Audio'],    icon: this.ICONS.headphones, wishlisted:false },
    { id:4,  name:'PadPro 12.9" M3',        brand:'Apple',    category:'tablet',     price:1099.00,                        rating:5, reviews:376, badge:'New',   specs:['M3 chip','12.9" Liquid XDR','WiFi 6'], icon: this.ICONS.tablet,     wishlisted:false },
    { id:5,  name:'ChronoFit Ultra 2',      brand:'Garmin',   category:'accessory',  price:449.00,  originalPrice:499.00, rating:4, reviews:203, badge:'Sale',  specs:['GPS','Heart Rate','7-day battery'],     icon: this.ICONS.watch,      wishlisted:false },
    { id:6,  name:'Alpha FX 7R V',          brand:'Sony',     category:'accessory',  price:3499.00,                        rating:5, reviews:142,                specs:['61MP','4K 120fps','Dual CFexpress'],    icon: this.ICONS.camera,     wishlisted:false },
    { id:7,  name:'Zenbook Pro Duo OLED',   brand:'ASUS',     category:'laptop',     price:1849.00, originalPrice:2099.00,rating:4, reviews:187, badge:'Sale',  specs:['Dual OLED','RTX 4070','64GB RAM'],      icon: this.ICONS.laptop,     wishlisted:false },
    { id:8,  name:'Pixel 9 Pro XL',         brand:'Google',   category:'smartphone', price:1099.00,                        rating:5, reviews:634, badge:'New',   specs:['6.8" LTPO','Tensor G4','7yr updates'], icon: this.ICONS.phone,      wishlisted:false },
    { id:9,  name:'AirPods Max 2',          brand:'Apple',    category:'audio',      price:549.00,                         rating:5, reviews:1024,badge:'Hot',   specs:['H2 chip','ANC','30hr battery'],         icon: this.ICONS.headphones, wishlisted:false },
  ];

  filteredProducts: Product[] = [];

  // ─── Lifecycle ────────────────────────────────────────────────────────────
  ngOnInit(): void {
    this.applyFilters();
  }

  // ─── Filtering & Sorting ──────────────────────────────────────────────────
  selectCategory(key: string): void {
    this.selectedCategory = key;
    this.applyFilters();
  }

  onSearch(): void {
    this.applyFilters();
  }

  onSort(): void {
    this.applyFilters();
  }

  clearSearch(): void {
    this.searchQuery = '';
    this.applyFilters();
  }

  private applyFilters(): void {
    let result = [...this.products];

    // Category filter
    if (this.selectedCategory !== 'all') {
      result = result.filter(p => p.category === this.selectedCategory);
    }

    // Search filter
    if (this.searchQuery.trim()) {
      const q = this.searchQuery.toLowerCase();
      result = result.filter(p =>
        p.name.toLowerCase().includes(q)  ||
        p.brand.toLowerCase().includes(q) ||
        p.specs.some(s => s.toLowerCase().includes(q))
      );
    }

    // Sort
    switch (this.sortBy) {
      case 'price-asc':  result.sort((a, b) => a.price - b.price);                    break;
      case 'price-desc': result.sort((a, b) => b.price - a.price);                    break;
      case 'rating':     result.sort((a, b) => b.rating - a.rating || b.reviews - a.reviews); break;
      case 'newest':     result.sort((a, b) => (b.badge === 'New' ? 1 : 0) - (a.badge === 'New' ? 1 : 0)); break;
      default: break; // featured — original order
    }

    this.filteredProducts = result;
  }

  // ─── Cart & Wishlist ──────────────────────────────────────────────────────
  addToCart(product: Product): void {
    this.cartCount++;
    // TODO: inject CartService and call cartService.add(product)
  }

  toggleWishlist(product: Product): void {
    product.wishlisted = !product.wishlisted;
    // TODO: inject WishlistService and sync
  }
}