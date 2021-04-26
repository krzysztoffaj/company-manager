import axios from "axios";
import React, { useState, Component } from "react";
import Table from "./Table";

class Employees extends Component {
  constructor(props) {
    super(props);

    this.state = {
      employees: [],
    };
  }

  componentDidMount() {
    axios.get("http://localhost:8080/api/employees").then((response) => {
      const employees = response.data;
      this.setState({ employees: employees });
    });
  }

  render() {
    return (
      <div>
        <Table employees={this.state.employees} />
      </div>
    );
  }
}

export default Employees;
