import { Component, DoCheck, EventEmitter, HostListener, OnDestroy, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SidebarService } from '../../service/sidebar/sidebar.service';
import { NavbarService } from '../../service/navbar/navbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit, DoCheck {
  isUserLoggedIn: boolean = false;
  user: string = null;
  isSideNavOpen: boolean = false;
  isMobileScreen: boolean = false;
  isNavbarContentVisible: boolean = false; // Controls navbar visibility on mobile
  isAdminLogin = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private snakBar: MatSnackBar,
    private sidebarService: SidebarService,
    private navbarService: NavbarService
  ) {}


  // Keep the user info up-to-date on every change
  ngDoCheck(): void {
    this.user = localStorage.getItem('username');
  }

  // Resize event listener to handle mobile screen size changes
  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.isMobileScreen = window.innerWidth <= 768; // Mobile screen width threshold
  }

  ngOnInit(): void {
    // Set the mobile screen status on initial load
    this.isMobileScreen = window.innerWidth <= 768;
    
    // Subscribe to the logged-in status from the AuthService
    this.authService.isLoggedIn.subscribe(res => {
      this.isUserLoggedIn = res;
      this.user = localStorage.getItem('username');
    });

    this.authService.isAdminLogin$.subscribe(res=>{
      this.isAdminLogin = res;
    })
  }

  // Handle logout
  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
    this.snakBar.open('Logout successfully', 'Close', {
      duration: 1000,
      horizontalPosition: 'center',
      verticalPosition: 'top',
    });
  }

  // Toggle sidebar status
  toggleSidenav() {
    this.isSideNavOpen = !this.isSideNavOpen;
    this.sidebarService.setSidebarStatus(this.isSideNavOpen);
    this.isNavbarContentVisible = !this.isNavbarContentVisible;
  }
}