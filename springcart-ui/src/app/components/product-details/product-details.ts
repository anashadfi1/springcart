import { Component, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '../../models/Product.model';

export interface CartItem {
  product: Product;
  quantity: number;
}

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-details.html',
})
export class ProductDetailsComponent implements OnChanges {
  @Input()  product: Product | null = null;

  @Output() closed          = new EventEmitter<void>();
  @Output() addedToCart     = new EventEmitter<CartItem>();
  @Output() wishlistToggled = new EventEmitter<Product>();

  quantity = 1;

  ngOnChanges(): void {
    // Reset quantity whenever a new product is shown
    this.quantity = 1;
  }

  increment(): void {
    this.quantity = Math.min(this.quantity + 1, 99);
  }

  decrement(): void {
    this.quantity = Math.max(this.quantity - 1, 1);
  }

  onAddToCart(): void {
    if (!this.product || !this.product.inStock) return;
    this.addedToCart.emit({ product: this.product, quantity: this.quantity });
  }

  onWishlistToggle(): void {
    if (!this.product) return;
    this.product.wishlisted = !this.product.wishlisted;
    this.wishlistToggled.emit(this.product);
  }

  onClose(): void {
    this.closed.emit();
  }
}