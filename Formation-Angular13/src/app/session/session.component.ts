import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';

import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ApiService } from '../Service/api.service';
import { FormationDialogComponent } from '../formation-dialog/formation-dialog.component';
import { NotificationService } from '../core/service/notification.service';
import { SessionDialogComponent } from '../session-dialog/session-dialog.component';
import { SessionDetailsComponent } from '../session-details/session-details.component';





export interface DialogData {
  animal: 'panda' | 'unicorn' | 'lion';
}
const ELEMENT_DATA: any[] = [
  
];
@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent {
  id: string | null;


  displayedColumns: string[] = ['date_debut','date_fin', 'nb_participant','formateur','organisme','lieu','action','participants'];
  dataSource = ELEMENT_DATA;
  @ViewChild(MatTable, { static: true })
  table!: MatTable<any>;


  constructor(public dialog: MatDialog, private apiService: ApiService,private notifyService : NotificationService,private _Activatedroute:ActivatedRoute) {
    this.id=this._Activatedroute.snapshot.paramMap.get("id");
    this.refreshUsers();
   }



  private refreshUsers() {
    this.apiService.apiGetAll(`/Formation/${this.id}`).subscribe((users: any) => {
      if (users) {
        this.dataSource = users.session_formation;
    console.log("Sessions",this.dataSource);
    this.table.renderRows();
      }
    },
      (error) => {
        console.log(error);
      });
  }

  openDialog(action: any,obj:  any) {
    obj.action = action;
    const dialogRef = this.dialog.open(SessionDialogComponent, {
      width: '250px',
      data:obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result.event == 'Add'){
        this.addRowData(result.data);       

      }else if(result.event == 'Update'){
        this.updateRowData(result.data);
      }else if(result.event == 'Delete'){
        this.deleteRowData(result.data);
      }
    });
  }

  addRowData(row_obj: any){
    var d = new Date();
    console.log({
      "date_debut": row_obj.date_debut,
      "date_fin": row_obj.date_fin,
      "nb_participant": row_obj.nb_participant,
      "organisme": {
        "id": row_obj.organisme.id,
    },
      "lieu": row_obj.lieu,
      "formateur":{
        "id": row_obj.formateur.id,
    },
    "formation":{
      "id":this.id,
  },

    });
    
    this.apiService.apiPost('/Session/AddSession',{
      "date_debut": row_obj.date_debut,
      "date_fin": row_obj.date_fin,
      "nb_participant": row_obj.nb_participant,
      "organisme": {
        "id": row_obj.organisme.id,
    },
      "lieu": row_obj.lieu,
      "formateur":{
        "id": row_obj.formateur.id,
    },
    "formation":{
      "id":this.id,
  },

    }).subscribe( (response: any) =>{
      this.notifyService.showSuccess("Session Added", "Create")

      this.refreshUsers();
      this.table.renderRows();

    })

    
  }
  updateRowData(row_obj: any){
    this.dataSource = this.dataSource.filter((value: any,key: any)=>{
      let id ;
      if(value.id == row_obj.id){
      /*   value.login=row_obj.login;
        value.pwd=row_obj.pwd;
        value.role=row_obj.role; */
        value = row_obj ;
    
        
      }
    console.log("updzating the object rn",row_obj);

      this.apiService.apiPut(`/Session/updateSession/${row_obj.id}`,{
        "date_debut": row_obj.date_debut,
        "date_fin": row_obj.date_fin,
        "nb_participant": row_obj.nb_participant,
        "organisme": {
          "id": row_obj.organisme.id,
      },
        "lieu": row_obj.lieu,
        "formateur":{
          "id": row_obj.formateur.id,
      },
     
      "formation":{
        "id":this.id,
    },

      }).subscribe( (response: any) =>{
        this.refreshUsers();
        this.notifyService.showSuccess("Session Updated", "Update")

      })
  
      return true;
    });
  }
  deleteRowData(row_obj: { id: any; }){


    this.dataSource = this.dataSource.filter((value: any,key: any)=>{
      return value.id != row_obj.id;
    });
    this.apiService.apiDelete(`/Formation/deleteFormation/${row_obj.id}`).subscribe( (response: any) =>{
        console.log(response);
      this.notifyService.showSuccess("Session Deleted", "Delete")

    })

  }

  openDetails(id : any,nb_participant:any){
    this.dialog.open(SessionDetailsComponent, {
      data: {
        id: id,
        nb_participant:nb_participant,
      },
    });
  }

}
