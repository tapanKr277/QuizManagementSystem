import { Component, OnInit } from "@angular/core";
import { ChartConfiguration, ChartOptions } from "chart.js";
import { UserService } from "../../../service/user/user.service";



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  isLoading = false;
  userId  = null;

  public lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [
      
    ],
    datasets: [
      {
        data: [ ],
        label: 'Number of Quizs',
        fill: true,
        tension: 0.5,
        borderColor: 'black',
        backgroundColor: 'rgba(255,0,0,0.3)'
      }
    ]
  };
  public lineChartOptions: ChartOptions<'line'> = {
    responsive: false
  };
  public lineChartLegend = true;


  // Pie
  public pieChartOptions: ChartOptions<'pie'> = {
    responsive: false,
  };
  public pieChartLabels = [ [ 'Download', 'Sales' ], [ 'In', 'Store', 'Sales' ], 'Mail Sales' ];
  public pieChartDatasets = [ {
    data: [ 300, 500, 100 ]
  } ];
  public pieChartLegend = true;
  public pieChartPlugins = [];

  //bar
  public barChartLegend = true;
  public barChartPlugins = [];

  public barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: [ '2006', '2007', '2008', '2009', '2010', '2011', '2012' ],
    datasets: [
      { data: [ 65, 59, 80, 81, 56, 55, 40 ], label: 'Series A' },
      { data: [ 28, 48, 40, 19, 86, 27, 90 ], label: 'Series B' }
    ]
  };

  public barChartOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: false,
  };


  // Doughnut
  public doughnutChartLabels: string[] = [ 'Download Sales', 'In-Store Sales', 'Mail-Order Sales' ];
  public doughnutChartDatasets: ChartConfiguration<'doughnut'>['data']['datasets'] = [
      { data: [ 350, 450, 100 ], label: 'Series A' },
      { data: [ 50, 150, 120 ], label: 'Series B' },
      { data: [ 250, 130, 70 ], label: 'Series C' }
    ];

  public doughnutChartOptions: ChartConfiguration<'doughnut'>['options'] = {
    responsive: false
  };


  constructor(private userService: UserService) {
    
  }
  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');
    if(this.userId){
      this.getUserQuizAttemptMonthAndYearWise(this.userId, 2025);
    }
  }

  getUserQuizAttemptMonthAndYearWise(userId: string, year: number): void {
    this.isLoading = true;
    
    this.userService.getUserNumberOfQuizAttemptMonthWise(userId, year).subscribe(
      (res: any[]) => {  // Assert the type as array
        console.log(res);
    
        const months = [
          'January', 'February', 'March', 'April', 'May', 'June', 'July',
          'August', 'September', 'October', 'November', 'December'
        ];
    
        // Set the labels to all 12 months (no need to clear them as we keep all months)
        this.lineChartData.labels = months;
    
        // Initialize the data array with 12 zeros (one for each month)
        this.lineChartData.datasets[0].data = new Array(12).fill(0);
    
        // Process the response and update the data array for specific months
        res.forEach((item: any) => {
          const month = Object.keys(item)[0];  // Extract month (key, like "1" for January)
          const attemptsCount = item[month];   // Extract attempts count (value)
    
          // Update the data array at the correct month index (0-based index)
          const monthIndex = parseInt(month) - 1; // January (1) maps to index 0, February (2) maps to index 1, etc.
          
          // Override the value for the correct month
          this.lineChartData.datasets[0].data[monthIndex] = attemptsCount;
        });
    
        this.isLoading = false;
      },
      (error) => {
        console.error('Error fetching quiz attempts:', error);
        this.isLoading = false;
      }
    );
  }
  

}
