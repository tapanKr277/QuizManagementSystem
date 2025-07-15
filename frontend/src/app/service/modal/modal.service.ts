import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  private isUserModalOpenSubject = new BehaviorSubject<boolean>(false);
  private isRoleModalOpenSubject = new BehaviorSubject<boolean>(false);
  private isOtpModalOpenSubject = new BehaviorSubject<boolean>(false);
  private isQuestionModalOpenSubject = new BehaviorSubject<boolean>(false);
  private isQuizModalOpenSubject = new BehaviorSubject<boolean>(false);
  private isModalOpenSubject = new BehaviorSubject<boolean>(false);
  private isDeleteModalOpen = new BehaviorSubject<boolean>(false);
  private isEmailTokenModalOpenSubject = new BehaviorSubject<boolean>(false);

  constructor() { }

  get deleteModalStatus$(){
    return this.isDeleteModalOpen.asObservable();
  }

  setDeleteModalOpen(status: boolean){
    this.isDeleteModalOpen.next(status);
  }

  // Getter for User Modal
  get userModalStatus$() {
    return this.isUserModalOpenSubject.asObservable();
  }

  // Setter for User Modal
  setUserModalOpen(status: boolean) {
    this.isUserModalOpenSubject.next(status);
  }

  // Getter for Role Modal
  get roleModalStatus$() {
    return this.isRoleModalOpenSubject.asObservable();
  }

  // Setter for Role Modal
  setRoleModalOpen(status: boolean) {
    this.isRoleModalOpenSubject.next(status);
  }

  // Getter for OTP Modal
  get otpModalStatus$() {
    return this.isOtpModalOpenSubject.asObservable();
  }

  // Setter for OTP Modal
  setOtpModalOpen(status: boolean) {
    this.isOtpModalOpenSubject.next(status);
  }

  // Getter for Question Modal
  get questionModalStatus$() {
    return this.isQuestionModalOpenSubject.asObservable();
  }

  // Setter for Question Modal
  setQuestionModalOpen(status: boolean) {
    this.isQuestionModalOpenSubject.next(status);
  }

  // Getter for Quiz Modal
  get quizModalStatus$() {
    return this.isQuizModalOpenSubject.asObservable();
  }

  // Setter for Quiz Modal
  setQuizModalOpen(status: boolean) {
    this.isQuizModalOpenSubject.next(status);
  }

  // Getter for Generic Modal
  get modalStatus$() {
    return this.isModalOpenSubject.asObservable();
  }

  // Setter for Generic Modal
  setModalOpen(status: boolean) {
    this.isModalOpenSubject.next(status);
  }

  // Getter for Email Token Modal
  get emailTokenModalStatus$() {
    return this.isEmailTokenModalOpenSubject.asObservable();
  }

  // Setter for Email Token Modal
  setEmailTokenModalOpen(status: boolean) {
    this.isEmailTokenModalOpenSubject.next(status);
  }
}
