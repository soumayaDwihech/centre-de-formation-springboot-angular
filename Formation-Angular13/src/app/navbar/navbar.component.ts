import { Component, OnInit } from '@angular/core';
import { ApiService } from '../core/service/api.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user : any ;
  logged_in : any ;
  roles : any ;
  constructor( private apiService : ApiService) { }

  ngOnInit(): void {
    this.user = this.apiService.getCurrentUser() ;
    this.logged_in = this.apiService.getLoggedin();
   if (this.logged_in){
     this.roles= JSON.parse(localStorage.getItem("account")||'{}').roles
   }
  }

  logout () {
    this.apiService.setLoggedOut();
    this.logged_in = false ;
  }

}
