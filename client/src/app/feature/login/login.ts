import { Component } from '@angular/core';
import { FormsModule, NgForm, NgModel } from '@angular/forms';
import { AuthService } from '../../core/services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  constructor(private auth: AuthService, private router: Router) {}
  ngOnInit() {
    if (this.auth.isLoggedIn()) {
      this.router.navigate(['/todos']);
    }
  }
  onSubmit(form: NgForm) {
    this.auth.Login();
    this.router.navigate(['/todos']);
  }
}
