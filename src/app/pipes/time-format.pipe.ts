import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'timeFormat'
})
export class TimeFormatPipe implements PipeTransform {

  transform(value: number): string {
    if (value < 0) return '00:00'; // Handle negative values (if needed)
    
    const minutes = Math.floor(value / 60); // Calculate remaining minutes
    const seconds = value % 60; // Calculate remaining seconds

    // Format the time as MM:SS with leading zero for single-digit numbers
    return `${this.padZero(minutes)}:${this.padZero(seconds)}`;
  }

  // Helper function to pad single-digit numbers with a leading zero
  padZero(num: number): string {
    return num < 10 ? `0${num}` : `${num}`;
  }

}
