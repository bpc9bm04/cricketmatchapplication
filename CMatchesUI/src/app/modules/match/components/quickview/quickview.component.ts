import { Component, OnInit } from '@angular/core';

import { Match } from '../../match';
import { MatchService} from '../../match.service';

@Component({
  selector: 'match-quickview',
  templateUrl: './quickview.component.html',
  styleUrls: ['./quickview.component.css']
})
export class QuickviewComponent implements OnInit {

  matches: Array<Match>;
  quickviewAPI: boolean;
  constructor(private matchService: MatchService) {
    this.matches = [];
    this.quickviewAPI = true;
    
   }

  ngOnInit() {
    this.getMatchesQuickView();

  }

  getMatchesQuickView(){
    this.matchService.getMatchesQuickView().subscribe((matches => {
      this.matches.push(...matches);
    }));
  }
}
