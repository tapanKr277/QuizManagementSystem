import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { QuizComponent } from './components/quiz/quiz.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ExamComponent } from './components/exam/exam.component';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { authGuard } from './shared/auth.guard';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { VerifyOtpComponent } from './components/verify-otp/verify-otp.component';
import { CreatePasswordComponent } from './components/create-password/create-password.component';
import { PricingComponent } from './components/pricing/pricing.component';
import { UserManagementComponent } from './components/admin/user-management/user-management.component';
import { RoleManagementComponent } from './components/admin/role-management/role-management.component';
import { QuizManagementComponent } from './components/admin/quiz-management/quiz-management.component';
import { QuestionManagementComponent } from './components/admin/question-management/question-management.component';
import { StudentManagementComponent } from './components/admin/student-management/student-management.component';
import { QuizAttemptsComponent } from './components/admin/quiz-attempts/quiz-attempts.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { EmailVerificationComponent } from './components/email-verification/email-verification.component';
import { CreateQuizComponent } from './components/create-quiz/create-quiz.component';
import { UploadPdfComponent } from './components/admin/upload-pdf/upload-pdf.component';
import { adminGuard } from './shared/admin.guard';
import { Oauth2RedirectHandlerComponent } from './components/oauth/oauth2-redirect-handler/oauth2-redirect-handler.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent },
  { path: 'quiz', component: QuizComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [authGuard]},
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'oauth2/redirect', component: Oauth2RedirectHandlerComponent },
  { path: 'exam/:quizId', component: ExamComponent, canActivate: [authGuard]},
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'verify-otp/:email', component: VerifyOtpComponent },
  { path: 'pricing', component: PricingComponent },
  { path: 'create-password/:email', component: CreatePasswordComponent },
  { path: 'verify-email', component: EmailVerificationComponent, },
  { path: 'admin/create-quiz', component: CreateQuizComponent, canActivate: [adminGuard]},
  { path: 'admin-dashboard', component: AdminDashboardComponent,},
  { path: 'admin/users', component: UserManagementComponent ,},
  { path: 'admin/roles', component: RoleManagementComponent, },
  { path: 'admin/quizzes', component: QuizManagementComponent,  },
  { path: 'admin/questions', component: QuestionManagementComponent,  },
  { path: 'admin/students', component: StudentManagementComponent,  },
  { path: 'admin/quiz-attempts', component: QuizAttemptsComponent, },
  { path: 'admin/upload-pdf', component: UploadPdfComponent, },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
}
