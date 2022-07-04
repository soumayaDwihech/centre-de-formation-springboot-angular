import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from '../Service/api.service';

export interface UsersData {
  name: string;
  id: number;
}

@Component({
  selector: 'app-session-dialog',
  templateUrl: './session-dialog.component.html',
  styleUrls: ['./session-dialog.component.css']
})
export class SessionDialogComponent   {


  organismes : any;
  formateurs : any;
  action:string;
  local_data:any;

  constructor(
    public apiService : ApiService,
    public dialogRef: MatDialogRef<SessionDialogComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: UsersData) {
    console.log(data);
    this.local_data = {...data};
    this.action = this.local_data.action;
    console.log("local data for pop up ",this.local_data);
    

    this.apiService.apiGetAll('/organisme/allOrganisme').subscribe((data: any) => {
      this.organismes = data ;
      this.local_data.organisme = data[0];
      console.log(this.organismes);
      
    })

    
    this.apiService.apiGetAll("/Formateur/allFormateurs").subscribe((data : any)=>{
      console.log("origanismes",data);
      this.formateurs = data ; 
      if (this.action == "Add"){
       
        this.local_data.formateur = {id : data[0]};
        
      }       
    })

  }

  

  doAction(){
    this.dialogRef.close({event:this.action,data:this.local_data});
  }

  closeDialog(){
    this.dialogRef.close({event:'Cancel'});
  }

}
