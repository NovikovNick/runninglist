import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import * as Store from "../store/ReduxActions";
import * as REST from "../rest/rest";
import {Dropdown} from "react-bootstrap";
import {useTranslation} from "react-i18next";

import ProfileModalForm from "../component/ProfileModalForm";


class Menu extends Component {

    constructor(props) {
        super(props);
        this.state = {
            profileForm: {
                uiSchema: {active: false},
                formData: {tags: []},
                onSubmit: (REST.saveProfile),
                onSuccess: this.onProfileSuccessUpdate,
                closeForm: this.toggleProfileForm
            },
            ...props
        };
        REST.getUserProfile().then(props.actions.setUser);
    }

    onProfileSuccessUpdate = () => {

        const that = this;
        REST.getTaskList()
            .then(runningList => {
                that.setState({runningList: runningList});
                that.state.actions.setRunningList(runningList);
            });

        this.toggleProfileForm();
    }

    toggleProfileForm = () => {
        const {profileForm} = this.state
        profileForm.uiSchema.active = !profileForm.uiSchema.active;
        this.setState({profileForm: profileForm})
    }

    render() {
        const {profileForm} = this.state;
        const {user, runningList} = this.props;

        profileForm.formData.tags = runningList.allTags;

        return (
            <Dropdown className={"taskmanager-profile w-100"}>

                <Dropdown.Toggle split id="dropdown-split-basic">{user.username}</Dropdown.Toggle>

                <Dropdown.Menu>
                    <Profile onClick={this.toggleProfileForm}/>
                    <Signout/>
                </Dropdown.Menu>
                <ProfileModalForm schema={profileForm}/>
            </Dropdown>
        )
    }
}

function Signout() {
    const {t} = useTranslation();
    const signout = () => REST.signOut().then(() => window.location = "/signin")

    return (<Dropdown.Item onClick={signout}>{t("signout")}</Dropdown.Item>);
}

function Profile({onClick}) {
    const {t} = useTranslation();

    return (<Dropdown.Item onClick={onClick}>{t("Profile")}</Dropdown.Item>);
}

const mapStateToProps = state => ({
    user: state.task.user,
    runningList: state.task.runningList
});

const mapDispatchToProps = dispatch => ({
    actions: bindActionCreators(Store, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(Menu);