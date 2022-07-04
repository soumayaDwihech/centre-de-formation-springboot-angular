import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from '../Service/api.service';


export interface UsersData {
  name: string;
  id: number;
}

@Component({
  selector: 'app-participant-dialog',
  templateUrl: './participant-dialog.component.html',
  styleUrls: ['./participant-dialog.component.css']
})
export class ParticipantDialogComponent implements OnInit {
  
  
  
  origanismes : any ;
  pays : any ;
  profil : any ;
  national : boolean;
  action:string;
  local_data:any;
  
  
    local_form :FormGroup ;
  constructor(
    public apiService: ApiService,
    public dialogRef: MatDialogRef<ParticipantDialogComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: UsersData) {
    this.local_data = {...data};
    this.action = this.local_data.action;
    if (this.action == "Add")
      this.local_data = {
        nom:"",
        prenom:"",
        email:"",
        tel:"",
        type:"Participant_National",
        organisme : {id : 1},
        pays : {id : 1},
        profil : {id : 1}

      }
      this.apiService.apiGetAll("/organisme/allOrganisme").subscribe((data : any)=>{
        console.log("origanismes",data);
        this.origanismes = data ; 
        if (this.action == "Add"){
         
          this.local_data.organisme = {id : data[0]};
          
        }       
      })

      this.apiService.apiGetAll("/pays/allPays").subscribe((data : any)=>{
        console.log("pays",data);
        this.pays = data ; 
        if (this.action == "Add"){
          this.local_data.pays = {id :data[0]};
        }       
      })

      this.apiService.apiGetAll("/Profil/profiles").subscribe((data : any)=>{
        console.log("profukes",data);
        this.profil = data ; 
        if (this.action == "Add"){
          this.local_data.profil = {id :data[0]}
        }       
      })

      if (this.local_data.type == "Participant_National")
      this.national = true ;
    else
     this.national= false ; 
      
      console.log("local data ",this.local_data);
      
      this.local_form= new FormGroup({
        nom: new FormControl(this.local_data.nom ),
        prenom: new FormControl( this.local_data.prenom,),
        email: new FormControl(this.local_data.email,),
        tel: new FormControl(this.local_data.tel,),
        type: new FormControl(this.local_data.type,),
        pays: new FormControl(this.local_data.pays.id,),
        profil: new FormControl(this.local_data.profil.id,),
        organisme: new FormControl(this.local_data.organisme.id),
     }
      );


  }

  ngOnInit() {
    this.local_form.valueChanges.subscribe(x => {
      if (x.type == "Participant_National")
{        this.national = true ;
        x.type =  1 ;}
      else
       this.national= false ; 
  })
   
  }

  doAction(){
  
    const id  =  this.local_data.id ;
    this.local_data = this.local_form.value;
    this.local_data.pays = {id : this.local_form.value.pays};

 
    this.local_data.organisme = {id : this.local_form.value.organisme};


    this.local_data.profil = {id : this.local_form.value.profil};
    this.local_data.id = id;
    this.dialogRef.close({event:this.action,data:this.local_data});
  }

  closeDialog(){
    this.dialogRef.close({event:'Cancel'});
  }

}
