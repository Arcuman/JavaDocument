import React, { Component } from "react";

class FooterComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <div>
        <footer className="footer">
          <span className="text-muted">
            All Rights Reserved 2020
            <a href="https://vk.com/12anton21"> @Arcuman</a>
          </span>
        </footer>
      </div>
    );
  }
}

export default FooterComponent;