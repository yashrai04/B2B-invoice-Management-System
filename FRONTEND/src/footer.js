import React from 'react'

export default function Footer() {
    return (
        <div style={{ display: "flex", justifyContent: "center", width: "100%", marginTop: "18px", background: "white" }}>
            <div style={{ borderRight: "2px solid grey" }}>
                <a href='https://highradius.com/' style={{ marginRight: "8px", color: "#1C5CC4", }}>
                    Privacy Policy
                </a>
            </div>
            <span style={{ marginLeft: "12px", color: "grey" }}>
                © 2023 Highradius.All Rights Reserved.
            </span>
        </div>
    )
}
