import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { MatSnackBar} from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Match} from '../../match';
import { MatchService} from '../../match.service';
import { MatchdetailsComponent } from '../matchdetails/matchdetails.component';


@Component({
  selector: 'match-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  match : Match;
  @Input()
  quickviewAPI: boolean;
  @Input()
  favouriteAPI: boolean;
  @Output()
  addMatch = new EventEmitter();
  @Output()
  deleteMatch = new EventEmitter();
  
  viewDetails: boolean;

 
  constructor(private matchService: MatchService, private snackBar : MatSnackBar, private dialog: MatDialog) { 
   
  }

  ngOnInit() {
    this.viewDetails = false;
    if(this.match.unique_id ==''){
      this.viewDetails = true;
    }
  }

  addMatchToFavourite(){
   
    console.log('inside emit ....');
    this.addMatch.emit(this.match);
    
  }

  deleteMatchFromFavouritelist(){
    console.log('delete match from thumbnail....' + this.match.unique_id);
    this.deleteMatch.emit(this.match);
  }

  getMatchDetails(){
    console.log('inside thumnail component ...,.getting  match details.......');
    let dialogRef = this.dialog.open(MatchdetailsComponent, {
      width: '430px',
      data: {obj: this.match}
    });
    console.log('dialog opened ');
    dialogRef.afterClosed().subscribe((result) => {
      console.log('after dialog closed -- result is --- '+result);
    });
  }
}
