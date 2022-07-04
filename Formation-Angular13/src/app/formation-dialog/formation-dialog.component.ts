import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from '../Service/api.service';

export interface UsersData {
  name: string;
  id: number;
}

@Component({
  selector: 'app-formation-dialog',
  templateUrl: './formation-dialog.component.html',
  styleUrls: ['./formation-dialog.component.css']
})
export class FormationDialogComponent {


  action:string;
  local_data:any;
  domaines : any ;

  constructor(
    public apiService : ApiService,
    public dialogRef: MatDialogRef<FormationDialogComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: UsersData) {
    console.log(data);
    this.local_data = {...data};
    this.action = this.local_data.action;

    this.apiService.apiGetAll('/domaine/domaines').subscribe((data: any) => {
      this.domaines = data ;
      this.local_data.domaine = data[0];
      console.log(this.domaines);
      
    })
  }

  doAction(){
    this.dialogRef.close({event:this.action,data:this.local_data});
  }

  closeDialog(){
    this.dialogRef.close({event:'Cancel'});
  }

}
