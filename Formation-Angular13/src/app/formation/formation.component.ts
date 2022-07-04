import { Component, OnInit, ViewChild } from '@angular/core';

import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ApiService } from '../Service/api.service';
import { FormationDialogComponent } from '../formation-dialog/formation-dialog.component';
import { NotificationService } from '../core/service/notification.service';



// export interface UsersData {
//   id: number;

// 	   login: String;
// 	   pwd: String	;

// }
const ELEMENT_DATA: any[] = [
  
];

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent implements OnInit {
  displayedColumns: string[] = ['titre','type_formation', 'nb_session','duree','budget','annee','domaine','action'];
  dataSource = ELEMENT_DATA;
  @ViewChild(MatTable, { static: true })
  table!: MatTable<any>;


  constructor(public dialog: MatDialog, private apiService: ApiService,private notifyService : NotificationService) { }

  ngOnInit(): void {
    this.refreshUsers();
    

  }

  private refreshUsers() {
    this.apiService.apiGetAll('/Formation/allFormations').subscribe((users: any) => {
      if (users) {
        this.dataSource = users;
    console.log(this.dataSource);
    this.table.renderRows();
      }
    },
      (error) => {
        console.log(error);
      });
  }

  openDialog(action: any,obj:  any) {
    obj.action = action;
    const dialogRef = this.dialog.open(FormationDialogComponent, {
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
    
    this.apiService.apiPost('/Formation/Formation',{
      "titre": row_obj.titre,
      "type_formation": row_obj.type_formation,
      "nb_session": row_obj.nb_session,
      "duree": row_obj.duree,
      "budget": row_obj.buget,
      "annee": row_obj.annee,
      "domaine": {
        "id": row_obj.domaine.id,
    },
    }).subscribe( (response: any) =>{
      this.notifyService.showSuccess("Formation Added", "Create")

      this.refreshUsers();
      this.table.renderRows();
      // this.dataSource.push({
      //   id:response.id,
      //   login:response.login,
      //   pwd:response.pwd,
  
  
      // });
      // this.table.renderRows();
    

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

      this.apiService.apiPut(`/Formation/Updateformation/${row_obj.id}`,{
          "titre": row_obj.titre,
          "type_formation": row_obj.type_formation,
          "nb_session": row_obj.nb_session,
          "duree": row_obj.duree,
          "budget": row_obj.budget,
          "annee": row_obj.annee,
          "domaine": {
            "id": row_obj.domaine.id,
        },


      }).subscribe( (response: any) =>{
        this.refreshUsers();
        this.notifyService.showSuccess("Formation Updated", "Update")

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
      this.notifyService.showSuccess("Formation Deleted", "Delete")

    })

  }

}
