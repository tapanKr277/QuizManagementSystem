import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";  // Import BrowserModule
import { AppRoutingModule } from "./app-routing.module";  // Import AppRoutingModule
import { FormsModule } from "@angular/forms";  

import { AppComponent } from "./app.component";  // Import AppComponent
import { LoginComponent } from "./components/login/login.component";
import { HomeComponent } from "./components/home/home.component";
import { RegisterComponent } from "./components/register/register.component";
import { ProfileComponent } from "./components/profile/profile.component";
import { QuizComponent } from "./components/quiz/quiz.component";
import { ExamComponent } from "./components/exam/exam.component";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { FooterComponent } from "./components/footer/footer.component";
import { AboutComponent } from "./components/about/about.component";
import { ContactComponent } from "./components/contact/contact.component";
import { ForgotPasswordComponent } from "./components/forgot-password/forgot-password.component";
import { LoaderComponent } from "./components/loader/loader.component";
import { SidebarComponent } from "./components/sidebar/sidebar.component";
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient, withFetch } from "@angular/common/http";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { TokenInterceptor } from "./service/interceptor/token.interceptor";
import { SubmitModalComponent } from './components/modal/submit-modal/submit-modal.component';
import { VerifyOtpComponent } from './components/verify-otp/verify-otp.component';
import { CreatePasswordComponent } from './components/create-password/create-password.component';
import { PricingComponent } from './components/pricing/pricing.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatIconModule} from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { RoleManagementComponent } from './components/admin/role-management/role-management.component';
import { QuizManagementComponent } from './components/admin/quiz-management/quiz-management.component';
import { QuestionManagementComponent } from './components/admin/question-management/question-management.component';
import { StudentManagementComponent } from './components/admin/student-management/student-management.component';
import { QuizAttemptsComponent } from './components/admin/quiz-attempts/quiz-attempts.component';
import { UserManagementComponent } from './components/admin/user-management/user-management.component';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { DeleteModalComponent } from './components/modal/delete-modal/delete-modal.component';
import { EditUserFormModalComponent } from './components/modal/edit-user-form-modal/edit-user-form-modal.component';
import { EditQuizFormModalComponent } from './components/modal/edit-quiz-form-modal/edit-quiz-form-modal.component';
import { EditStudentFormModalComponent } from './components/modal/edit-student-form-modal/edit-student-form-modal.component';
import { EditQuestionFormModalComponent } from './components/modal/edit-question-form-modal/edit-question-form-modal.component';
import { EditOtpFormModalComponent } from './components/modal/edit-otp-form-modal/edit-otp-form-modal.component';
import { EditTokenFormModalComponent } from './components/modal/edit-token-form-modal/edit-token-form-modal.component';
import { EditUserRoleFormModalComponent } from './components/modal/edit-user-role-form-modal/edit-user-role-form-modal.component';
import { EditQuizQuestionIdsFormModalComponent } from './components/modal/edit-quiz-question-ids-form-modal/edit-quiz-question-ids-form-modal.component';
import { TimeFormatPipe } from './pipes/time-format.pipe';
import { OtpManagementComponent } from './components/admin/otp-management/otp-management.component';
import { TokenManagementComponent } from './components/admin/token-management/token-management.component';
import { TableLoaderComponent } from './components/table-loader/table-loader.component';
import { CustomSideNavComponent } from './components/custom-side-nav/custom-side-nav.component';
import { EmailVerificationComponent } from './components/email-verification/email-verification.component';
import { WarningModalComponent } from './components/modal/warning-modal/warning-modal.component';
import { MyProfileComponent } from './components/profile/my-profile/my-profile.component';
import { DashboardComponent } from './components/profile/dashboard/dashboard.component';
import { AttemptsComponent } from './components/profile/attempts/attempts.component';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { BaseChartDirective } from 'ng2-charts';
import { DatetimeLocalPipe } from './pipes/datetime-local.pipe';
import { CreateQuizComponent } from './components/create-quiz/create-quiz.component';
import { UploadPdfComponent } from './components/admin/upload-pdf/upload-pdf.component';
import { ChatbotComponent } from './components/chatbot/chatbot.component';
import { Oauth2RedirectHandlerComponent } from './components/oauth/oauth2-redirect-handler/oauth2-redirect-handler.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    ProfileComponent,
    QuizComponent,
    ExamComponent,
    NavbarComponent,
    FooterComponent,
    AboutComponent,
    ContactComponent,
    ForgotPasswordComponent,
    LoaderComponent,
    SidebarComponent,
    PageNotFoundComponent,
    VerifyOtpComponent,
    CreatePasswordComponent,
    PricingComponent,
    AdminDashboardComponent,
    RoleManagementComponent,
    QuizManagementComponent,
    QuestionManagementComponent,
    StudentManagementComponent,
    QuizAttemptsComponent,
    UserManagementComponent,
    DeleteModalComponent,
    SubmitModalComponent,
    EditUserFormModalComponent,
    EditQuizFormModalComponent,
    EditStudentFormModalComponent,
    EditQuestionFormModalComponent,
    EditOtpFormModalComponent,
    EditTokenFormModalComponent,
    EditUserRoleFormModalComponent,
    EditQuizQuestionIdsFormModalComponent,
    TimeFormatPipe,
    OtpManagementComponent,
    TokenManagementComponent,
    TableLoaderComponent,
    CustomSideNavComponent,
    EmailVerificationComponent,
    WarningModalComponent,
    MyProfileComponent,
    DashboardComponent,
    AttemptsComponent,
    DatetimeLocalPipe,
    CreateQuizComponent,
    UploadPdfComponent,
    ChatbotComponent,
    Oauth2RedirectHandlerComponent
    ],
  imports: [
    BrowserModule,  
    AppRoutingModule,  
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatDialogModule,
    MatPaginatorModule,
    MatSelectModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    BaseChartDirective
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass:TokenInterceptor, multi:true},
    provideHttpClient(withFetch()),
    provideAnimationsAsync(),
    provideCharts(withDefaultRegisterables())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
