import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';

import { Match } from '../../match';
import { MatchService} from '../../match.service';


@Component({
  selector: 'match-cric-container',
  templateUrl: './cric-container.component.html',
  styleUrls: ['./cric-container.component.css']
})
export class CricContainerComponent implements OnInit {

  matches: Array<Match>;
  
  constructor(private matchService: MatchService, private route: ActivatedRoute) {
    //this.matches = [];
   }

  ngOnInit() {
    
   

    this.matchService.getMatches().subscribe((response => {
      this.matches = response;
     
      console.log('matches --->',this.matches);
      }));
  }

}
