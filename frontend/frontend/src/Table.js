import React from "react";

const Table = ({ employees }) => {
  return (
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>position</th>
          <th>salary</th>
          <th>Supervisor</th>
        </tr>
      </thead>
      <tbody>
        {employees.map((employee) => {
          return (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.position}</td>
              <td>{employee.salary}</td>
              <td>
                {employee.supervisor === null
                  ? ""
                  : employee.supervisor.firstName +
                    " " +
                    employee.supervisor.lastName}
              </td>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};
export default Table;
