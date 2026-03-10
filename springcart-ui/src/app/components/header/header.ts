import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface HeroStat {
  value: string;
  label: string;
}

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.html',
})
export class HeaderComponent {
  @Input() badgeText      = 'New Arrivals 2025';
  @Input() headlineLine1  = 'Next-Gen';
  @Input() headlineLine2  = 'Electronics';
  @Input() subtitle       = 'Explore the latest laptops, smartphones, audio gear and accessories — curated for performance enthusiasts.';
  @Input() ctaLabel       = 'Shop Now';
  @Input() secondaryLabel = 'View Deals';
  @Input() stats: HeroStat[] = [
    { value: '2K+',  label: 'Products'   },
    { value: '50+',  label: 'Brands'     },
    { value: '4.9★', label: 'Avg Rating' },
  ];

  @Output() ctaClicked       = new EventEmitter<void>();
  @Output() secondaryClicked = new EventEmitter<void>();

  onCtaClick():       void { this.ctaClicked.emit();       }
  onSecondaryClick(): void { this.secondaryClicked.emit(); }
}