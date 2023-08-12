import { Grid, makeStyles } from "@material-ui/core";
import CompanyLogo from "./companyLogo.svg";
import Logo from "./logohighradiuscolor.png";
import React from "react";

const useStyles = makeStyles({
    main: {
        padding: "3vh 0",
        paddingLeft: "30px",
        backgroundColor: "white",
        display: "flex",
        justifyContent: "center",
    },
    redtxt: {
        color: "red",
        paddingTop: "15px"
    },
    company: {
        alignItems: "left",
    },
    companyName: {
        textAlign: "left",
        fontFamily: "serif",
        fontWeight: "600",
        letterSpacing: "0px",
        opacity: 1,
        marginLeft: "2.5vw",
        marginTop: "0.5vh",
        fontSize: "1.5rem",
        widht: "25vw",
        height: "5vh",
        color: "gray"
    },
    hrcLogo: {
        textAlign: "center",
        width: "15vw",
        height: "5vh",
        border: "0px solid white",
    },
    comLogo: {
        float: "left",
        widht: "5vw",
        height: "5vh",
    },
    invoiceHeader: {
        color: "#fff",
        fontSize: "1rem",
        paddingBottom: "30px",
        paddingTop: "15px",
    },

});

const Header = () => {
    const classes = useStyles();
    return (
        <div className={classes.main}>
            <Grid container>
                <Grid container item xs={12}>
                    <Grid item xs={4}>
                        <img
                            src={CompanyLogo}
                            alt="companylogo"
                            className={classes.comLogo}
                        />
                        <h4 className={classes.companyName}>ABC Products</h4>
                    </Grid>

                    <Grid item xs={4} style={{ textAlign: "center" }}>
                        <img src={Logo} alt="highradius-logo" className={classes.hrcLogo} />
                    </Grid>
                    <Grid item xs={4}></Grid>
                </Grid>
                <h3 className={classes.redtxt}>Invoice list</h3>
            </Grid>

        </div>
    );
};

export default Header;