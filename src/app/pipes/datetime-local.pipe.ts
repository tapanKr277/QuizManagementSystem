import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'datetimeLocal'
})
export class DatetimeLocalPipe implements PipeTransform {

  transform(value: Date): string {
    if (!value) return '';
    const date = new Date(value);
    return date.toISOString().slice(0, 16); // Format as YYYY-MM-DDTHH:mm
  }

}
