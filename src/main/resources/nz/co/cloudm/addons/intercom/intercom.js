window.nz_co_cloudm_addons_intercom_IntercomIntegration = function () {

    var self = this;
    this.apiLoaded = false;

    this.loadScript = function (state) {
        window.intercomSettings = {};
        self.updateSettings(state);

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
                    s.src = 'https://widget.intercom.io/widget/' + state.appId;
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

    this.updateSettings = function(state) {
        window.intercomSettings['app_id'] = state.appId;

        if (state.userId) {
            window.intercomSettings['user_id'] = state.userId;
        } else {
            delete window.intercomSettings['user_id'];
        }

        if (state.userEmail) {
            window.intercomSettings['email'] = state.userEmail;
        } else {
            delete window.intercomSettings['email'];
        }

        if (state.userHash) {
            window.intercomSettings['user_hash'] = state.userHash;
        } else {
            delete window.intercomSettings['user_hash'];
        }

        // Merge user data into intercom settings. Might not work for IE11.
        window.intercomSettings = Object.assign(window.intercomSettings, state.userData);
    };

    this.onStateChange = function() {
        if (!self.apiLoaded) {
            self.loadScript(self.getState());
            self.apiLoaded = true;
        } else {
            self.updateSettings(self.getState());
        }
    };

    this.shutdown = function() {
        Intercom('shutdown');
    };

    this.boot = function() {
        Intercom('boot', intercomSettings);
    };

    this.update = function() {
        Intercom('update', intercomSettings);
    }
};
