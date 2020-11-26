import React, { Component } from "react";

class HomeComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div style={{ paddingTop: "100px" }} className=" text-center">
        <h2>Javalabs 2-8</h2>
        <h3>Borisov Anton</h3>
      </div>
    );
  }
}

export default HomeComponent;
