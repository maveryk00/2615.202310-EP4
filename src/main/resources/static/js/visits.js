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



    const btnRegister = document.querySelector('#btn-register')
    btnRegister.addEventListener('click', (event) => {
        registerVisit()
    })

    getVisits()


    function registerVisit() {
        let formData = new FormData()
        formData.append('local', document.querySelector('#local').value)
        formData.append('dni', document.querySelector('#dni').value)
        formData.append('date', document.querySelector('#date').value)
        formData.append('time', document.querySelector('#time').value)

        var object = {};
        formData.forEach((value, key) => object[key] = value);
        console.log(object)


        fetch('/api/visits', {
                method: 'POST',
                body: JSON.stringify(object),
                headers: {
                      'Content-Type': 'application/json'
                    }
            })
            .then(response => response.json())
            .catch(error => console.error('Error:', error))
            .then(response => {
                console.log(response)
                M.toast({html: response.message})

                if (response.status == 201)
                    getVisits()
            })
    }

    function updateVisit(visit) {
        let object = {
            local: visit.local,
            dni: visit.dni,
            date: visit.date,
            time: visit.time
        }

        fetch(`/api/visits/${visit.id}`, {
                method: 'PUT',
                body: JSON.stringify(object),
                headers: {
                      'Content-Type': 'application/json'
                    }
            })
            .then(response => response.json())
            .catch(error => console.error('Error:', error))
            .then(response => {
                console.log(response)
                M.toast({html: response.message})

                if (response.status == 200)
                    getVisits()
            })
    }

    function deleteVisit(visit) {
        fetch(`/api/visits/${visit.id}`, {
                method: 'DELETE'
            })
            .then(response => response.json())
            .catch(error => console.error('Error:', error))
            .then(response => {
                console.log(response)
                M.toast({html: response.message})

                if (response.status == 200)
                    getVisits()
            })
    }



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
         const rowActionsTemplate =
         `<button class="btn btn-edit btn-flat waves-effect waves-light btn-small">
              <i class="material-icons">edit</i>
          </button>
          <button class="btn btn-delete btn-flat waves-effect waves-light btn-small">
              <i class="material-icons">delete</i>
          </button>`

        const tbody = document.querySelector('#tbl-visits tbody')
        tbody.innerHTML = ''

        visits.forEach(visit => {
            const tr = document.createElement('tr')

            Object.entries(visit).forEach(([key, value]) => {
                const td = document.createElement('td')
                td.setAttribute('data-name', key)
                td.innerText = value

                tr.appendChild(td)
            })

            const td = document.createElement('td')
            td.innerHTML += rowActionsTemplate

            const btnEdit = td.querySelector('.btn-edit')
            btnEdit.addEventListener('click', (event) => {
                convertToForm(tr)
            })

            const btnDelete = td.querySelector('.btn-delete')
            btnDelete.addEventListener('click', (event) => {
                deleteVisit(visit)
            })

            tr.appendChild(td)

            tbody.appendChild(tr)
        })
    }

    function convertToForm(row) {
        const formActionsTemplate =
        `<button class="btn btn-submit btn-flat waves-effect waves-light btn-small">
             <i class="material-icons">send</i>
         </button>
         <button class="btn btn-cancel btn-flat waves-effect waves-light btn-small">
             <i class="material-icons">cancel</i>
         </button>`

        let cellsData = row.querySelectorAll('td:not(td:last-child)')
        let cellsActions = row.querySelector('td:last-child')

        cellsData.forEach((cell, index) => {
            let input = document.createElement('input')
            input.setAttribute('type', 'text')
            input.setAttribute('name', cell.getAttribute('data-name'))
            input.setAttribute('value', cell.innerText)

             if (index == 0 || index == 2)
                input.setAttribute('disabled', '')

            cell.innerHTML = ''
            cell.appendChild(input)
        })

        cellsActions.innerHTML = formActionsTemplate
        let btnSubmit = cellsActions.querySelector('.btn-submit')
        let btnCancel = cellsActions.querySelector('.btn-cancel')

        btnSubmit.addEventListener('click', (event) => {
            let visit = {
                id: row.querySelector('[name=id]').value,
                local: row.querySelector('[name=local]').value,
                dni: row.querySelector('[name=dni]').value,
                date: row.querySelector('[name=date]').value,
                time: row.querySelector('[name=time]').value
            }
            updateVisit(visit)
        })

        btnCancel.addEventListener('click', (event) => {
            cancelForm(row)
        })
    }

    function cancelForm(row) {
        const rowActionsTemplate =
         `<button class="btn btn-edit btn-flat waves-effect waves-light btn-small">
              <i class="material-icons">edit</i>
          </button>
          <button class="btn btn-delete btn-flat waves-effect waves-light btn-small">
              <i class="material-icons">delete</i>
          </button>`

        let cellsData = row.querySelectorAll('td:not(td:last-child)')
        let cellsActions = row.querySelector('td:last-child')

        cellsData.forEach((cell, index) => {
            let input = cell.querySelector('input')
            let value = input.value

            cell.setAttribute('data-name', input.getAttribute('name'))
            cell.innerHTML = value
        })

        cellsActions.innerHTML = rowActionsTemplate
        let btnEdit = cellsActions.querySelector('.btn-edit')
        let btnDelete = cellsActions.querySelector('.btn-delete')

        btnEdit.addEventListener('click', (event) => {
            convertToForm(row)
        })

        btnDelete.addEventListener('click', (event) => {
            let visit = {
                id: row.querySelector('[data-name="id"]').innerText
            }
            deleteVisit(visit)
        })
    }

}