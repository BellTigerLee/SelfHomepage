const _ = {
    async request(url, httpMethod, parameters) {
        const options = {
            method: httpMethod,
            headers:{
                "Accept": 'application/json',
                "Content-Type": "application/json",
            },
            body: JSON.stringify(parameters),
        };
        const res = await fetch(url);
        const data = await res.json();
        if(res.ok)
            return data;
        else
            throw Error(data);
    },

    $(selector) {
        return document.querySelector(selector);
    },

    eventHandler(selector, event, callback) {
        const docu = this.$(selector);
        if(docu === null) { return; }
        docu.addEventListener(event, callback);
    },

    _byString(object, string) {
        string = string.replace(/\[(\w+)\]/g, '.$1'); // convert indexes to properties
        string = string.replace(/^\./, '');           // strip a leading dot
        const tokens = string.split('.');
        for (let i = 0, n = tokens.length; i < n; ++i) {
            let k = tokens[i];
            if (k in object) {
                object = object[k];
            } else {
                return;
            }
        }
        return object;
    }
};

const API = {
    BOARDS: {
        MYBOARD: "/boards",
        BOARD(bno){
            return `/boards/${bno}`;
        }
    }
};

export {_,  API};