import React, {useRef, useState} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {useTranslation} from "react-i18next";
import {Formik} from 'formik';
import {Button, Col, Form, Modal, Row} from 'react-bootstrap';
import {FormTags} from "../vendor/Tags/index";
import * as Store from "../../store/ReduxActions";
import PropTypes from "prop-types";


function CreateTaskModalForm({isActive, toggle, actions, runningList}) {

    const {t} = useTranslation();
    const [errors, setErrors] = useState({});
    const [valid, setValid] = useState({});
    const titleInput = useRef();

    const onSubmit = (values, {resetForm}) => {

        actions.createTask(values)
            .then(res => {
                resetForm({});
                toggle();
            })
            .catch(response => {

                setErrors(response)

                // if key doesn't present in errors, then it is valid
                var valid = Object.keys(values).reduce(function (obj, k) {
                    if (!response.hasOwnProperty(k)) obj[k] = values[k];
                    return obj;
                }, {});
                setValid(valid);
            });
    };

    return (
        <Formik
            enableReinitialize
            initialValues={{tags: []}}
            onSubmit={onSubmit}>

            {({
                  handleChange,
                  setFieldValue,
                  handleSubmit,
                  values,
                  touched,
                  resetForm
              }) => (

                <Modal show={isActive}
                       onShow={() => titleInput.current.focus()}
                       onHide={() => {
                           setErrors({})
                           resetForm({});
                           toggle();
                       }}>
                    <Form noValidate onSubmit={handleSubmit}>

                        <Modal.Header closeButton>
                            <Modal.Title>{t('create_task_title')}</Modal.Title>
                        </Modal.Header>

                        <Modal.Body>

                            <Form.Group as={Row} controlId="validationFormik01">
                                <Form.Label column sm="3">{t('title')}</Form.Label>
                                <Col sm={'9'}>
                                    <Form.Control
                                        ref={titleInput}
                                        name="title"
                                        onChange={(v) => {
                                            if (errors && errors.title) {
                                                const {title, ...rest} = errors;
                                                setErrors(rest);
                                            }
                                            if (valid && valid.title) {
                                                const {title, ...rest} = valid;
                                                setValid(rest);
                                            }
                                            handleChange(v);
                                        }}
                                        defaultValue={values.title}
                                        isValid={touched.title && valid.title}
                                        isInvalid={!!errors.title}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors.title}</Form.Control.Feedback>
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} controlId="validationFormik02">
                                <Form.Label column sm="3">{t('description')}</Form.Label>
                                <Col sm={'9'}>
                                    <Form.Control
                                        as="textarea"
                                        name="description"
                                        onChange={(v) => {
                                            if (errors && errors.description) {
                                                const {description, ...rest} = errors;
                                                setErrors(rest);
                                            }
                                            if (valid && valid.description) {
                                                const {description, ...rest} = valid;
                                                setValid(rest);
                                            }
                                            handleChange(v);
                                        }}
                                        defaultValue={values.description}
                                        isValid={touched.description && valid.description}
                                        isInvalid={!!errors.description}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors.description}</Form.Control.Feedback>
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} controlId="validationFormik03">
                                <Form.Label column sm="3">{t('tags')}</Form.Label>
                                <Col sm={'9'}>
                                    <FormTags tags={values.tags || []}
                                              placeholder={t("Add tags")}
                                              suggestions={runningList.allTags || []}
                                              handleDelete={(i) => setFieldValue('tags', values.tags.filter((tag, index) => index !== i))}
                                              handleAddition={(tag) => setFieldValue('tags', [...values.tags, tag])}/>
                                    <div className={"invalid-feedback"}>{errors.tags}</div>
                                </Col>
                            </Form.Group>

                        </Modal.Body>

                        <Modal.Footer>
                            <Button type="submit">{t('create_task_submit_btn')}</Button>
                        </Modal.Footer>

                    </Form>
                </Modal>
            )}
        </Formik>
    );
}

CreateTaskModalForm.propTypes = {
    isActive: PropTypes.bool.isRequired,
    toggle: PropTypes.func.isRequired,
    actions: PropTypes.object.isRequired,
    runningList: PropTypes.shape({
        calendar: PropTypes.object.isRequired,
        tasks: PropTypes.array.isRequired,
        selectedTags: PropTypes.array.isRequired,
        allTags: PropTypes.array.isRequired
    })
};

const mapStateToProps = state => ({
    runningList: state.task.runningList
});
const mapDispatchToProps = dispatch => ({
    actions: bindActionCreators(Store, dispatch)
});
export default connect(mapStateToProps, mapDispatchToProps)(CreateTaskModalForm);
