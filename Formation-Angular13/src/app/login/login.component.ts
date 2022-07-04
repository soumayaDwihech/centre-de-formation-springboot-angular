import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiService } from 'src/app/core/service/api.service';
import { NotificationService } from '../core/service/notification.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private credential = {userName: '', password : ''};
  userName: string;
  password: string;
  account: any;
  constructor( private authService: ApiService, private router: Router,   private snackBar: MatSnackBar,private notifyService : NotificationService)  { }

  ngOnInit() {
    if (localStorage.getItem('token')) {
      this.router.navigate(['/formateurs']);
    }
  }

  login(): void {

    this.authService.sendCredential(this.userName, this.password).subscribe(
      data => {
       // this.token.saveToken(data.token);
        localStorage.setItem('account', JSON.stringify(data));
        localStorage.setItem('token', data.accessToken);
       // this.snackBar.open('Connected Sucessfully ');
        this.notifyService.showSuccess("Connected Sucessfully", "Authentification")

          this.authService.setLoggedin();
          this.router.navigate(['/formateurs']);
        
      },
      error => {
        console.log(error);
        //this.snackBar.open('Failed to connect');
        this.notifyService.showError("Connection Failed","Authentification");
      },



    );


  }



}
