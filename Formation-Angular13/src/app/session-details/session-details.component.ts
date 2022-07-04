import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSelectionList } from '@angular/material/list';
import { NotificationService } from '../core/service/notification.service';
import { ApiService } from '../Service/api.service';
import { DialogData } from '../session/session.component';

@Component({
  selector: 'app-session-details',
  templateUrl: './session-details.component.html',
  styleUrls: ['./session-details.component.css']
})
export class SessionDetailsComponent implements OnInit {
  participants: any[] = [];
  session:any ;
  selectedParticipants:any ;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private apiService: ApiService,private notifyService : NotificationService) {}

  ngOnInit(): void {


    this.apiService.apiGetAll("/Participant/allParticipants").subscribe((data : any)=>{
      this.participants = data ; 

      this.apiService.apiGetAll(`/Session/${this.data.id}`).subscribe((session : any)=>{
        this.session = session ;
        session.participant.map((p1:any) =>{
          this.participants.map((p2:any) =>{
            if (p1.id == p2.id ){
              p1.checked = true ;
              p2.checked = true ;
              
            }
          })
        })
        
        
      })

    })


  }

  save ( ){
    this.session.participant = this.selectedParticipants;
    console.log("new participants",this.session);

         this.apiService.apiPut(`/Session/updateSession/${this.data.id}`,this.session).subscribe((session : any)=>{
          this.notifyService.showSuccess("Participants Updated", "Success")

        },
          (error) => {
          this.notifyService.showError("An error has occured", "Error")

            console.log(error);
          });
    
  }

}
