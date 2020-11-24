import React, { Component } from "react";
import DocumentService from "../services/DocumentService";

class ListDocumentsComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      documents: [],
    };
    this.addDocument = this.addDocument.bind(this);
    this.editDocument = this.editDocument.bind(this);
    //this.deleteDocument = this.deleteDocument.bind(this);
  }

  componentDidMount() {
    DocumentService.getDocuments().then((res) => {
      this.setState({ documents: res.data });
    });
  }

  editDocument(id) {
    this.props.history.push(`/add-document/${id}`);
  }

  addDocument() {
    this.props.history.push("/add-document/_add");
  }

  render() {
    return (
      <div>
        <h2 className="text-center">Documents List</h2>
        <div className="row">
          <button className="btn btn-primary" onClick={this.addDocument}>
            {" "}
            Add Document
          </button>
        </div>
        <br></br>
        <div className="row">
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th> Document Title</th>
                <th> Document Description</th>
                <th> Document FileName</th>
                <th> Actions</th>
              </tr>
            </thead>
            <tbody>
              {this.state.documents.map((document) => (
                <tr key={document.id}>
                  <td> {document.title} </td>
                  <td> {document.description}</td>
                  <td> {document.fileName}</td>
                  <td>
                    <button
                      onClick={() => this.editDocument(document.id)}
                      className="btn btn-info"
                    >
                      Update{" "}
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      onClick={() => this.deleteDocument(document.id)}
                      className="btn btn-danger"
                    >
                      Delete{" "}
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      onClick={() => this.viewDocument(document.id)}
                      className="btn btn-info"
                    >
                      View{" "}
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ListDocumentsComponent;
