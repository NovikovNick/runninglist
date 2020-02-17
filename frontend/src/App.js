import React, {Suspense} from 'react';
import {combineReducers, createStore} from 'redux';
import {Provider} from 'react-redux';
import {BrowserRouter as Router, Route} from "react-router-dom";

import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import * as reducers from './store/reducers';

import Loadable from 'react-loadable';
import Loading from "./component/Loading";

const store = createStore(combineReducers(reducers),
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

toast.configure();

const Page = Loadable({
    loader: () => import("./page/Page"),
    loading: Loading,
});

const LoginPage = Loadable({
    loader: () => import("./page/LoginPage"),
    loading: Loading,
});

const ChangePasswordPage = Loadable({
    loader: () => import("./page/ChangePasswordPage"),
    loading: Loading,
});

const ExpiredTokenPage = Loadable({
    loader: () => import("./page/ExpiredTokenPage"),
    loading: Loading,
});

const ServerUnavailablePage = Loadable({
    loader: () => import("./page/ServerUnavailablePage"),
    loading: Loading,
});


export default function App() {


    return (
        <Provider store={store}>
            <ToastContainer
                autoClose={1500}
                hideProgressBar={true}/>

            <Suspense fallback={<div>loading...</div>}>

                <Router>

                    <Route exact path="/" component={Page}/>
                    <Route path="/error" component={ServerUnavailablePage}/>
                    <Route path="/expired/token" component={ExpiredTokenPage}/>
                    <Route path="/signin" component={LoginPage}/>
                    <Route path="/changepassword" component={ChangePasswordPage}/>
                </Router>
            </Suspense>
        </Provider>
    );
}
