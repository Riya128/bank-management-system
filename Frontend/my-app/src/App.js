import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import UserRegisterForm from './components/Common/UserRegisterForm';
import MainHome from './components/Common/MainHome';
import Contact from './components/Common/Contact';
import AccountStatus from './components/Common/AccountStatus';
import Login from './components/Common/Login';
import AdminDashboard from './components/Admin/AdminDashboard';
import UserDashboard from './components/user/UserDashboard';
import GetAccountDetails from "./components/user/GetAccountDetails";
import GetAllBeneficiary from './components/user/GetAllBeneficiary';
import AddBeneficiary from './components/user/AddBeneficiary';
import TransferMoney from './components/user/TransferMoney';
import DepositMoney from './components/user/DepositMoney';
import Balance from './components/user/Balance';


function App() {
  return (
    <>


      <Router>
        <Routes>
          <Route path="/adminDashboard" element={<AdminDashboard />} />
          <Route path="/userDashboard" element={<UserDashboard />} />
          <Route exact path='/' element={<MainHome />} />
          <Route exact path="/contact" element={<Contact />} />
          <Route path='/register' element={<UserRegisterForm />} />
          <Route path='/login' element={<Login />} />

          <Route exact path="/getaccountdetails" element={<GetAccountDetails />} />
          <Route path="/accountStatus" element={<AccountStatus />} />
          <Route exact path='/getallbeneficiary' element={<GetAllBeneficiary />} />
          <Route exact path='/getaccountdetails' element={<GetAccountDetails />} />
          <Route exact path='/addbeneficiary' element={<AddBeneficiary />} />

          <Route exact path='/depositmoney' element={<DepositMoney />} />
          <Route exact path='/makepayment' element={<TransferMoney />} />
          <Route exact path='/viewbalance' element={<Balance />} />


        </Routes>
      </Router>
    </>
  )

}

export default App;
