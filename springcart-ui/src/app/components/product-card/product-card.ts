import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '../../models/Product.model';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-card.html',
})
export class ProductCardComponent {
  @Input({ required: true }) product!: Product;

  @Output() addToCart       = new EventEmitter<Product>();
  @Output() wishlistToggled = new EventEmitter<Product>();
  @Output() cardClicked     = new EventEmitter<Product>();

  onAddToCart():      void { this.addToCart.emit(this.product);       }
  onWishlistToggle(): void { this.wishlistToggled.emit(this.product); }
  onCardClick():      void { this.cardClicked.emit(this.product);     }
}