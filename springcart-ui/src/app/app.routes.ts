import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { SignupComponent } from './components/signup/signup';
import { ProductListComponent } from './components/product-list/product-list';
import { CartSidebarComponent } from './components/cart-sidebar/cart-sidebar';
import { ProductDetailsComponent } from './components/product-details/product-details';
export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'product-list', component: ProductListComponent },
  { path: 'products/:id', component: ProductDetailsComponent },
  { path: 'cart', component: CartSidebarComponent },
  { path: '', redirectTo: 'products', pathMatch: 'full' },
];