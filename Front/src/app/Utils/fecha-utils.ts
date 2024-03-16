export class FechaUtils{
  static getDatesArray(): string[] {
    const dates: string[] = [];
    const currentDate = new Date();
    const dateFormatOptions: Intl.DateTimeFormatOptions = { day: '2-digit', month: '2-digit', year: 'numeric' };

    // Obtener la fecha actual en formato DD-MM-YYYY
    dates.push(currentDate.toLocaleDateString('es-MX', dateFormatOptions));

    // Obtener las siguientes 15 fechas en formato DD-MM-YYYY
    for (let i = 1; i <= 15; i++) {
      const nextDate = new Date();
      nextDate.setDate(currentDate.getDate() + i);
      dates.push(nextDate.toLocaleDateString('es-MX', dateFormatOptions));
    }

    return dates;
  }

  static formatStrToStr2(dateString: string): string {
    const parts = dateString.split('-');

    // Verifica si la cadena tiene el formato correcto
    if (parts.length !== 3 || parts[0].length !== 2 || parts[1].length !== 2 || parts[2].length !== 4) {
      throw new Error('Formato de fecha inválido. Debe ser DD-MM-YYYY.');
    }

    const formattedDate = `${parts[2]}${parts[1]}${parts[0]}`;
    return formattedDate;
  }

}
