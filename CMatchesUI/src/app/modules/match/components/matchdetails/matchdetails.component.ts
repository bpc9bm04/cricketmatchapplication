import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatSnackBar} from '@angular/material/snack-bar';

import { MatchService } from '../../match.service';
import { Match } from '../../match';
import { Matchdetails } from '../../matchdetails';


@Component({
  selector: 'matchdetails-dialog',
  templateUrl: './matchdetails.component.html',
  styleUrls: ['./matchdetails.component.css']
})
export class MatchdetailsComponent implements OnInit {

  match: Match;
  comments: string;
  actionType: string;
  matchdetails: Matchdetails
  constructor(private snackbar: MatSnackBar, private matDialogRef: MatDialogRef<MatchdetailsComponent>,
  @Inject(MAT_DIALOG_DATA) public data: any, private matchService: MatchService ) { 

    this.match = data.obj;    
    this.actionType = data.actionType;
    this.matchdetails = new Matchdetails();
    console.log('match details --- unique id--->', this.match.unique_id);
    
    this.matchService.getMatchDetails(this.match.unique_id).subscribe(matchData => {
      console.log('matchData    ', matchData);
      this.matchdetails = matchData;
    });
  }

  ngOnInit() {
    /*console.log('match details --- unique id--->', this.match.unique_id);
    
    this.matchService.getMatchDetails(this.match.unique_id).subscribe(matchData => {
      console.log('matchData    ', matchData);
      this.matchdetails = matchData;
    });*/

    //console.log('matchdetails', this.matchdetails);
  }

  onCancelClick(){
    this.matDialogRef.close();
  }

  getMatchDetail(){
    console.log('match details --- unique id--->', this.match.unique_id);
  }
  
 
}
