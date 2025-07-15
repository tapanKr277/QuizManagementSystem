import { Component, OnDestroy, OnInit } from '@angular/core';
import { SidebarService } from '../../service/sidebar/sidebar.service';
import { ChangeDetectorRef } from '@angular/core';  // Import ChangeDetectorRef

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  isQuizAttempt: boolean = false;
  isSidenavOpen: boolean = false;
  userId: string = null;
  isLoading: boolean = false;
  quizAttemptList = [];
  tabName: string = 'quiz_attempts';

  constructor(private sidebarService: SidebarService, private cdRef: ChangeDetectorRef) {}

  ngOnDestroy(): void {
    this.sidebarService.setSidebarStatus(false);
  }

  ngOnInit(): void {
    if (localStorage.getItem("userId")) {
      this.userId = localStorage.getItem('userId');
    }

    // Subscribe to sidebar status and set isSidenavOpen
    this.sidebarService.getSidebarStatus().subscribe(status => {
      this.isSidenavOpen = status;
      // Manually trigger change detection to avoid NG0100 error
      this.cdRef.detectChanges();
    });
  }

  onSelectTab(event) {
    this.tabName = event;
  }
}
