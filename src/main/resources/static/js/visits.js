window.onload = (event) => {

    const selectElems = document.querySelectorAll('select')
    const selectInstances = M.FormSelect.init(selectElems)

    const datepickerElems = document.querySelectorAll('.datepicker')
    const datepickerInstances = M.Datepicker.init(datepickerElems, {
        format: 'yyyy-mm-dd',
        i18n: {
            months: [
                      'Enero',
                      'Febrero',
                      'Marzo',
                      'Abril',
                      'Mayo',
                      'Junio',
                      'Julio',
                      'Augost',
                      'Septiembre',
                      'Octubre',
                      'Noviembre',
                      'Deciembre'
                    ],
            monthsShort: [
                           'Ene',
                           'Feb',
                           'Mar',
                           'Abr',
                           'May',
                           'Jun',
                           'Jul',
                           'Ago',
                           'Sep',
                           'Oct',
                           'Nov',
                           'Dic'
                         ],
            weekdays: [
                        'Domingo',
                        'Lunes',
                        'Martes',
                        'Miércoles',
                        'Jueves',
                        'Viernes',
                        'Sabado'
                      ],
            weekdaysShort: [
                             'Dom',
                             'Lun',
                             'Mar',
                             'Mie',
                             'Jue',
                             'Vie',
                             'Sab'
                           ],
            weekdaysAbbrev: ['D','L','M','M','J','V','S']
        }
    })

    const timepickerElems = document.querySelectorAll('.timepicker')
    const timepickerInstances = M.Timepicker.init(timepickerElems, {
        twelveHour: false
    })


    getVisits()


    function getVisits() {
        fetch('/api/visits')
            .then(response => response.json())
            .catch(error => console.error('Error:', error))
            .then(response => {
                console.log(response)
                fillTableVisits(response)
            })
    }

    function fillTableVisits(visits) {
        const tbody = document.querySelector('tbody')

        visits.forEach(visit => {
            const tr = document.createElement('tr')

            Object.entries(visit).forEach(([key, value]) => {
                const td = document.createElement('td')
                td.innerText = value

                tr.appendChild(td)
            })

            tbody.appendChild(tr)
        })
    }

}