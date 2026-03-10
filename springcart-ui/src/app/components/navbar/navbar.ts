import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';

export interface NavLink {
  label: string;
  path: string;
  active?: boolean;
}

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './navbar.html',
})
export class NavbarComponent {
  @Input() cartCount     = 0;
  @Input() wishlistCount = 0;

  @Output() searchChanged = new EventEmitter<string>();

  searchQuery    = '';
  mobileMenuOpen = false;

  navLinks: NavLink[] = [
    { label: 'Products', path: '/products' },
    { label: 'Deals',    path: '/deals'    },
    { label: 'Brands',   path: '/brands'   },
    { label: 'Support',  path: '/support'  },
  ];

  onSearchChange(query: string): void {
    this.searchChanged.emit(query);
  }

  toggleMobileMenu(): void {
    this.mobileMenuOpen = !this.mobileMenuOpen;
  }
}