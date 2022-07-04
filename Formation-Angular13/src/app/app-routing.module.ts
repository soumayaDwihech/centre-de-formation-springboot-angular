import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './core/guards/auth-guard.service';
import { FormationComponent } from './formation/formation.component';
import { LoginComponent } from './login/login.component';
import { ParticipantsComponent } from './participants/participants.component';
import { SessionComponent } from './session/session.component';
import { SignupComponent } from './signup/signup.component';
import { UsersComponent } from './users/users.component';
const routes: Routes = [
  { path: '', redirectTo: 'formateurs', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'formateurs', component: UsersComponent ,canActivate: [AuthGuardService],},
  { path: 'formations', component: FormationComponent ,canActivate: [AuthGuardService],},
  { path: 'participants', component: ParticipantsComponent ,canActivate: [AuthGuardService],},
  { path: 'formation/:id', component: SessionComponent ,canActivate: [AuthGuardService],}


];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
