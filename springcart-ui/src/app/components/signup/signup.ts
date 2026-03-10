import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';

// Custom cross-field validator for password match
function passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
  const password = control.get('password')?.value;
  const confirmPassword = control.get('confirmPassword')?.value;
  return password && confirmPassword && password !== confirmPassword
    ? { passwordMismatch: true }
    : null;
}

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './signup.html',
})
export class SignupComponent {
  signupForm: FormGroup;
  errorMessage = '';
  successMessage = '';
  isLoading = false;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.signupForm = this.fb.group(
      {
        firstName: ['', [Validators.required]],
        lastName:  ['', [Validators.required]],
        email:     ['', [Validators.required, Validators.email]],
        password:  ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', [Validators.required]],
        acceptTerms: [false, [Validators.requiredTrue]],
      },
      { validators: passwordMatchValidator }
    );
  }

  // Returns the strength level 0–4 based on password value
  getPasswordStrength(): number {
    const pw: string = this.signupForm.get('password')?.value || '';
    let score = 0;
    if (pw.length >= 6)  score++;
    if (pw.length >= 10) score++;
    if (/[A-Z]/.test(pw) && /[a-z]/.test(pw)) score++;
    if (/\d/.test(pw) && /[^A-Za-z0-9]/.test(pw)) score++;
    return score;
  }

  getStrengthColor(index: number): string {
    const strength = this.getPasswordStrength();
    if (index >= strength) return 'bg-slate-700';
    if (strength === 1) return 'bg-red-500';
    if (strength === 2) return 'bg-amber-500';
    if (strength === 3) return 'bg-blue-400';
    return 'bg-emerald-500';
  }

  getStrengthTextColor(): string {
    const strength = this.getPasswordStrength();
    if (strength === 1) return 'text-red-400/70';
    if (strength === 2) return 'text-amber-400/70';
    if (strength === 3) return 'text-blue-400/70';
    if (strength === 4) return 'text-emerald-400/70';
    return 'text-slate-600';
  }

  getStrengthLabel(): string {
    const strength = this.getPasswordStrength();
    if (strength === 1) return 'Weak';
    if (strength === 2) return 'Fair';
    if (strength === 3) return 'Good';
    if (strength === 4) return 'Strong';
    return '';
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      this.signupForm.markAllAsTouched();
      return;
    }
    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    const { firstName, lastName, email, password } = this.signupForm.value;

    this.authService.register({ firstName, lastName, email, password }).subscribe({
      next: () => {
        this.successMessage = 'Account created! Redirecting to login…';
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
      error: (err: any) => {
        this.errorMessage = err.error?.message || 'Registration failed. Please try again.';
        this.isLoading = false;
      },
    });
  }
}