import { Component, OnInit, ViewChild } from '@angular/core';

import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';
import { ApiService } from '../Service/api.service';
import { NotificationService } from '../core/service/notification.service';
import { ParticipantDialogComponent } from '../participant-dialog/participant-dialog.component';

const ELEMENT_DATA: any[] = [
  
];

@Component({
  selector: 'app-participants',
  templateUrl: './participants.component.html',
  styleUrls: ['./participants.component.css']
})


export class ParticipantsComponent implements OnInit {


  displayedColumns: string[] = ['id', 'email','prenom','nom','tel',"type",'organisme','pays','profil','action'];
  dataSource = ELEMENT_DATA;
  @ViewChild(MatTable, { static: true })
  table!: MatTable<any>;


  constructor(public dialog: MatDialog, private apiService: ApiService,private notifyService : NotificationService) { }

  ngOnInit(): void {
    this.refreshUsers();
  }

  private refreshUsers() {
    this.apiService.apiGetAll('/Participant/allParticipants').subscribe((users: any) => {
      if (users) {
        this.dataSource = users
        console.log(users);
        
        this.table.renderRows();
      }
    },
      (error) => {
        console.log(error);
      });
  }

  openDialog(action: any,obj:  any) {
    obj.action = action;
    const dialogRef = this.dialog.open(ParticipantDialogComponent, {
      width: '250px',
      data:obj
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log("dialog closed", result);
      
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
    this.apiService.apiPost('/Participant/AddParticipant',{
      nom : row_obj.nom,
      prenom:row_obj.prenom,
      email:row_obj.email,
      tel:row_obj.tel,
      type:row_obj.type,
      pays:{id : row_obj.pays.id},
      profil:{id : row_obj.profil.id},
      organisme:{id :row_obj.organisme.id}
    }).subscribe( (response: any) =>{
      this.refreshUsers();
            this.notifyService.showSuccess("Participant Added", "Create")

      this.table.renderRows();
    })

  }
  updateRowData(row_obj: any){
    console.log("now in update row",row_obj);
    
    this.dataSource = this.dataSource.filter((value: any,key: any)=>{
      if(value.id == row_obj.id){
        
        value.nom = row_obj.nom
        value.prenom=row_obj.prenom
        value.email=row_obj.email
        value.tel=row_obj.tel
        value.type=row_obj.type
        value.organisme={id :row_obj.organisme.id}
        value.pays={id : row_obj.pays.id}
        value.profil={id : row_obj.profil.id}

        this.apiService.apiPut(`/Participant/updateParticipant/${row_obj.id}`,{
          nom : row_obj.nom,
          prenom:row_obj.prenom,
          email:row_obj.email,
          tel:row_obj.tel,
          type:row_obj.type,
          pays:{id :row_obj.pays.id},
          profil:{id : row_obj.profil.id},
          organisme:{id :row_obj.organisme.id}
        }).subscribe( (response: any) =>{
          this.refreshUsers();
          this.notifyService.showSuccess("Participant Updated", "Update")
  
        })

      }

  
      return true;
    });
  }
  deleteRowData(row_obj: { id: any; }){


    this.dataSource = this.dataSource.filter((value: any,key: any)=>{
      return value.id != row_obj.id;
    });
    this.apiService.apiDelete(`/Participant/deleteParticipant/${row_obj.id}`).subscribe( (response: any) =>{
      this.notifyService.showSuccess("Participant Deleted", "Delete")

        console.log(response);
    })

  }

}
