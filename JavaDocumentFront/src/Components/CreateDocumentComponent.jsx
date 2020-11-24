import React, { Component } from "react";
import DocumentService from "../services/DocumentService";
class CreateDocumentComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      // step 2
      id: this.props.match.params.id,
      title: "",
      description: "",
      fileName: "",
    };
    this.changeTitleHandler = this.changeTitleHandler.bind(this);
    this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
    this.saveOrUpdateDocument = this.saveOrUpdateDocument.bind(this);
  }

  componentDidMount() {
    if (this.state.id === "_add") {
      return;
    } else {
      DocumentService.getDocumentById(this.state.id).then((res) => {
        let document = res.data;
        this.setState({
          title: document.title,
          description: document.description,
          fileName: document.fileName,
        });
      });
    }
  }
  saveOrUpdateDocument = (e) => {
    e.preventDefault();
    let document = {
      title: this.state.title,
      description: this.state.description,
      fileName: this.state.fileName,
    };
    console.log("document => " + JSON.stringify(document));

    if (this.state.id === "_add") {
      DocumentService.createDocument(document).then((res) => {
        this.props.history.push("/documents");
      });
    } else {
      DocumentService.updateDocument(document, this.state.id).then((res) => {
        this.props.history.push("/documents");
      });
    }
  };

  changeTitleHandler = (event) => {
    this.setState({ title: event.target.value });
  };

  changeDescriptionHandler = (event) => {
    this.setState({ description: event.target.value });
  };

  changeFileNameHandler = (event) => {
    this.setState({ fileName: event.target.value });
  };

  cancel() {
    this.props.history.push("/documents");
  }

  getTitle() {
    if (this.state.id === "_add") {
      return <h3 className="text-center">Add Document</h3>;
    } else {
      return <h3 className="text-center">Update Document</h3>;
    }
  }
  render() {
    return (
      <div>
        <br></br>
        <div className="container">
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              {this.getTitle()}
              <div className="card-body">
                <form>
                  <div className="form-group">
                    <label> First Name: </label>
                    <input
                      placeholder="First Name"
                      name="title"
                      className="form-control"
                      value={this.state.title}
                      onChange={this.changeTitleHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Last Name: </label>
                    <input
                      placeholder="Last Name"
                      name="description"
                      className="form-control"
                      value={this.state.description}
                      onChange={this.changeDescriptionHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> FileName: </label>
                    <input
                      placeholder="FileName Address"
                      name="fileName"
                      className="form-control"
                      value={this.state.fileName}
                      onChange={this.changeFileNameHandler}
                    />
                  </div>

                  <button
                    className="btn btn-success"
                    onClick={this.saveOrUpdateDocument}
                  >
                    Save
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={this.cancel.bind(this)}
                    style={{ marginLeft: "10px" }}
                  >
                    Cancel
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CreateDocumentComponent;
