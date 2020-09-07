import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import Button from "@material-ui/core/Button";
import {Link} from "react-router-dom";

const columns = [
    {
        id: 'name',
        label: 'Name',
        minWidth: 170,
        align: 'right',
    },
    {
        id: 'startDate',
        label: 'Starting Date',
        minWidth: 170,
        align: 'right',
    },
    {
        id: 'endDate',
        label: 'End Date',
        minWidth: 170,
        align: 'right',
    },
    {
        id: 'quota',
        label: 'Quota',
        minWidth: 170,
        align: 'right',
    },
    {
        id: 'updateButton',
        minWidth: 170,
        label: 'Update Button',
        align: 'right',
    },
    {
        id: 'deleteButton',
        minWidth: 170,
        label: 'Delete Button',
        align: 'right',
    },
    {
        id: 'listButton',
        minWidth: 170,
        label: 'List Button',
        align: 'right',
    },
];

const useStyles = makeStyles((theme) => ({
    root: {
        width: '75%',
        marginTop: theme.spacing(2),
    },
    container: {
        maxHeight: 600,
    },
}));

export default function InstitutionPersonActivityTable(props) {
    const classes = useStyles();
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    return (
        <div style={{textAlign: "-moz-center"}}>
            <Button style={{marginTop: "16px"}} color="primary" variant="outlined"><Link to={'/' + props.username + "/new"}>Add Activity</Link></Button>
            <Paper className={classes.root}>
                <TableContainer className={classes.container}>
                    <Table stickyHeader aria-label="sticky table">
                        <TableHead>
                            <TableRow>
                                {columns.map((column) => (
                                    <TableCell
                                        key={column.id}
                                        align={column.align}
                                        style={{ minWidth: column.minWidth, backgroundColor: "rgb(125, 125, 125)", color: "white" }}
                                    >
                                        {column.label}
                                    </TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {props.rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((row) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.id}>
                                        {columns.map((column) => {
                                            const value = row[column.id];
                                            return (
                                                <TableCell key={column.id} align={column.align}>
                                                    {!(column.id === "updateButton" || column.id === "deleteButton" || column.id === "listButton") ? value : (
                                                        (column.id === "updateButton") ? (
                                                            <Button color="primary" variant="outlined">
                                                                <Link to={'/' + props.username + '/update/' + row.id}>Update</Link>
                                                            </Button>
                                                        ) : ( (column.id === "deleteButton") ?
                                                                (
                                                                    <Button color="secondary" variant="outlined" onClick={() => props.deleteActivity(row.id)}>
                                                                        Delete
                                                                    </Button>
                                                                ) :
                                                                (
                                                                    <Button color="primary" variant="outlined">
                                                                        <Link to={'/activities/' + row.id + '/person_list'}>List</Link>
                                                                    </Button>
                                                                )
                                                        )
                                                    )}
                                                </TableCell>
                                            );
                                        })}
                                    </TableRow>
                                );
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[10, 25, 100]}
                    component="div"
                    count={props.rows.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                    style={{backgroundColor: "rgb(125, 125, 125)", color: "white"}}
                />
            </Paper>
            <Button color="primary" variant="outlined" style={{marginTop: "16px"}}>
                <Link to={'/' + props.username + "/statistic"}>Get Statistics!</Link>
            </Button>
        </div>
    );
}