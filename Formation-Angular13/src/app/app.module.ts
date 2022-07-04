import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SharedModule } from './shared/shared.module';
import { ToastrModule } from 'ngx-toastr';

import { FormsModule ,ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './navbar/navbar.component';
import { UsersComponent } from './users/users.component';



import { ApiService } from './Service/api.service';
import { DialogBoxComponent } from './dialog-box/dialog-box.component';
import { LoginComponent } from './login/login.component';
import { HttpConfigInterceptor } from './core/interceptor/httpconfig.interceptor';
import { MAT_SNACK_BAR_DEFAULT_OPTIONS } from '@angular/material/snack-bar';
import { JwtHelperService, JwtModule, JwtModuleOptions, JWT_OPTIONS  } from '@auth0/angular-jwt';
import { FormationComponent } from './formation/formation.component';
import { FormationDialogComponent } from './formation-dialog/formation-dialog.component';
import { SignupComponent } from './signup/signup.component';
import { NotificationService } from './core/service/notification.service';
import { ParticipantsComponent } from './participants/participants.component';
import { ParticipantDialogComponent } from './participant-dialog/participant-dialog.component';
import { SessionComponent } from './session/session.component';
import { SessionDialogComponent } from './session-dialog/session-dialog.component';
import { SessionDetailsComponent } from './session-details/session-details.component';

const JWT_Module_Options: JwtModuleOptions = ({
  config: {
    tokenGetter: jwtTokenGetter
  }});


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    UsersComponent,
    DialogBoxComponent,
    LoginComponent,
    FormationComponent,
    FormationDialogComponent,
    SignupComponent,
    ParticipantsComponent,
    ParticipantDialogComponent,
    SessionComponent,
    SessionDialogComponent,
    SessionDetailsComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    SharedModule,
    ReactiveFormsModule,
    JwtModule.forRoot(JWT_Module_Options),
    ToastrModule.forRoot(),


  

  ],
  providers: [ApiService,NotificationService,
    { provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true },
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 2500}},
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService
],
  bootstrap: [AppComponent],
  
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
export function jwtTokenGetter() {
  return  localStorage.getItem('token');
}

