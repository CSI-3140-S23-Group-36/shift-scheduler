export {};
import Page from "../../page";
import { useNavigate } from "react-router-dom";

export default function NewUser() {

  return (
    <div>
      <Page>
      <div className="container">
      <h1>Employee Login</h1>
        <form>
        <label htmlFor="username" className="input-label">
          Employee's Username:
        </label>
        <input
          type="text"
          id="username"
          className="input-field"
          name="username"
        //   value={loginData.username}
        //   onChange={handleInputChange}
          required
        />
        <label htmlFor="password" className="input-label">
          Temporary Password:
        </label>
        <input
          type="password"
          id="password"
          className="input-field"
          name="password"
        //   value={loginData.password}
        //   onChange={handleInputChange}
          required
        />
        <div className="btn-group">
          <input type="submit" value="Submit" className="btn" />
          <input
            type="button"
            value="Cancel"
            className="btn"
            // onClick={handleCancel}
          />
        </div>
      </form>
    </div>
      </Page>
    </div>
  );
}
