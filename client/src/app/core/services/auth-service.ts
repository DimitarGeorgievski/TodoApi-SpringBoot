import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  isLoggedIn = signal<boolean>(false)
  Login(){
    this.isLoggedIn.set(true)
  }
  Logout(){
    this.isLoggedIn.set(false)
  }
}
