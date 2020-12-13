import React, { Component } from "react";
import DocumentService from "../../services/DocumentService";
class Search extends Component {
  state = {
    query: "",
  };

  handleInputChange = () => {
    this.setState({
      query: this.search.value,
    });
  };

  render() {
    return (
      <form onSubmit={this.handleSubmit} className="row  mr-1 mb-1">
        <input
          className="mr-1"
          onChange={this.handleInputChange}
          ref={(input) => (this.search = input)}
        />
        <button type="submit" className="btn btn-info">
          Search
        </button>
        <p />
      </form>
    );
  }
}

export default Search;
