window.co_nz_blerter_addons_intercom_IntercomIntegration = function () {

    var self = this;
    this.apiLoaded = false;

    this.loadScript = function (id) {
        window.intercomSettings = {
            app_id: id
        };

        (function () {
            var w = window;
            var ic = w.Intercom;
            if (typeof ic === "function") {
                ic('reattach_activator');
                ic('update', intercomSettings);
            } else {
                var d = document;
                var i = function () {
                    i.c(arguments)
                };
                i.q = [];
                i.c = function (args) {
                    i.q.push(args)
                };
                w.Intercom = i;

                function l() {
                    var s = d.createElement('script');
                    s.type = 'text/javascript';
                    s.async = true;
                    s.src = 'https://widget.intercom.io/widget/' + id;
                    var x = d.getElementsByTagName('script')[0];
                    x.parentNode.insertBefore(s, x);
                }

                // if (w.attachEvent) {
                //     w.attachEvent('onload', l);
                // } else {
                //     w.addEventListener('load', l, false);
                // }

                // Page already loaded at this stage, so just insert the script
                l();
            }
        })()
    };

    this.onStateChange = function() {
        if (!self.apiLoaded) {
            self.loadScript(self.getState().appId);
        }
    };
};