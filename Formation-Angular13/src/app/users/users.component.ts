import { Component, OnInit, ViewChild } from '@angular/core';

import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';
import { ApiService } from '../Service/api.service';
import { NotificationService } from '../core/service/notification.service';



// export interface UsersData {
//   id: number;

// 	   login: String;
// 	   pwd: String	;

// }
const ELEMENT_DATA: any[] = [
  
];

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})


export class UsersComponent implements OnInit {
  displayedColumns: string[] = ['id', 'mail','pernom','nom','tel',"type",'organisme','action'];
  dataSource = ELEMENT_DATA;
  @ViewChild(MatTable, { static: true })
  table!: MatTable<any>;


  constructor(public dialog: MatDialog, private apiService: ApiService,private notifyService : NotificationService) { }

  ngOnInit(): void {
    this.refreshUsers();
  }

  private refreshUsers() {
    this.apiService.apiGetAll('/Formateur/allFormateurs').subscribe((users: any) => {
      if (users) {
        this.dataSource = users
        this.table.renderRows();
      }
    },
      (error) => {
        console.log(error);
      });
  }

  openDialog(action: any,obj:  any) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
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
    this.apiService.apiPost('/Formateur/addFormateur',{
      nom : row_obj.nom,
      pernom:row_obj.pernom,
      mail:row_obj.mail,
      tel:row_obj.tel,
      type:row_obj.type,
      organisme:{id :row_obj.organisme.id}
    }).subscribe( (response: any) =>{
      this.refreshUsers();
            this.notifyService.showSuccess("Formateur Added", "Create")

      this.table.renderRows();
    })

  }
  updateRowData(row_obj: any){
    this.dataSource = this.dataSource.filter((value: any,key: any)=>{
      if(value.id == row_obj.id){
        
        value.nom = row_obj.nom,
        value.pernom=row_obj.pernom,
        value.mail=row_obj.mail,
        value.tel=row_obj.tel,
        value.type=row_obj.type,
        value.organisme={id :row_obj.organisme.id}

      }
      this.apiService.apiPut(`/Formateur/updateFormateur/${row_obj.id}`,{
        nom : row_obj.nom,
        pernom:row_obj.pernom,
        mail:row_obj.mail,
        tel:row_obj.tel,
        type:row_obj.type,
        organisme:{id :row_obj.organisme.id}
      }).subscribe( (response: any) =>{
        this.refreshUsers();
        this.notifyService.showSuccess("Formateur Updated", "Update")

      })
  
      return true;
    });
  }
  deleteRowData(row_obj: { id: any; }){


    this.dataSource = this.dataSource.filter((value: any,key: any)=>{
      return value.id != row_obj.id;
    });
    this.apiService.apiDelete(`/Formateur/deleteFormateur/${row_obj.id}`).subscribe( (response: any) =>{
      this.notifyService.showSuccess("Formateur Deleted", "Delete")

        console.log(response);
    })

  }

}
