import React, {useState} from "react";
import setting from "../config";
import {useHistory} from "react-router-dom";
import LoadingOverlay from "react-loading-overlay";

export default function Authenticator({path, children}) {

    const [requestSent, setRequestSent] = useState(false);
    const [responseReceived, setResponseReceived] = useState(false);
    const history = useHistory();

    if (!requestSent) {
        const settings = {
            method: 'GET',
            credentials: 'include',
            cache: 'no-cache'
        };
        fetch(setting.API_URL + '/user', settings)
            .then(response => {

                setResponseReceived(true);

                if (response.status === 200) {
                    history.push(path)
                }
                if (response.status === 403) {
                    history.push("/signin")
                }
                if (response.status === 502) {
                    history.push("/error")
                }
            })
            .catch(error => {
                setResponseReceived(true);
                history.push("/error")
            });
        setRequestSent(true);
    }

    return (<LoadingOverlay active={!responseReceived} spinner>

        {children}

    </LoadingOverlay>)
}
