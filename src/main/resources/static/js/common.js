window.utils = window.utils || {}
$(function () {
    utils.dialog = {
        alert: function () {

        },
        confirm: function (msg, callback) {
            var modal = $('#confirm_modal');
            if(!modal || modal.length === 0) {
                var modal = '<div id="confirm_modal" class="modal fade" tabindex="-1" role="dialog">' +
                    '    <div class="modal-dialog" role="document">' +
                    '        <div class="modal-content">' +
                    '                <div class="modal-body">' +
                    '                   <p>' + msg + '</p>' +
                    '                </div>' +
                    '                <div class="modal-footer">' +
                    '                    <button id="confirm_btn" type="button" class="btn btn-primary">确认</button>' +
                    '                    <button id="cancel_btn" type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>' +
                    '                </div>' +
                    '        </div>' +
                    '    </div>' +
                    '</div>';
                $('body').append(modal);
                modal = $('#confirm_modal');
            }
            modal.modal({
                backdrop: 'static',
                show: true
            });
            modal.find('#confirm_btn').on('click', function () {
                modal.modal({
                    show: false
                })
                callback();
            })
        }
    }
});